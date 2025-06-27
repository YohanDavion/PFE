import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {PatientService} from '../services/patient.service';
import {OrthophonisteService} from '../services/orthophoniste.service';
import {AdministrateurService} from '../services/administrateur.service';
import {AuthService} from '../services/auth.service';
import {CommonModule} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../services/user.service';
import {User} from '../interfaces/user';

@Component({
  selector: 'app-parametres',
  templateUrl: './parametres.component.html',
  styleUrls: ['./parametres.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class ParametresComponent implements OnInit {
  parametresForm: FormGroup;
  userType: string = '';
  userId: number = 0;
  loading: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';
  isEditingPatient: boolean = false;
  currentUser!: User;


  constructor(
    private fb: FormBuilder,
    private userService : UserService,
    private patientService: PatientService,
    private orthophonisteService: OrthophonisteService,
    private administrateurService: AdministrateurService,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {
    this.parametresForm = this.fb.group({
      nom: [''],
      prenom: [''],
      email: ['', [Validators.required, Validators.email]],
      telephone: [''],
      adresse: [''],
      nomParent: [''],
      prenomParent: [''],
      rpps: [''],
      siret: [''],
      photo: [''],
      // numeroAdeli: ['']
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const patientId = params['patientId'];
      if (patientId) {
        this.isEditingPatient = true;
        this.userService.getUser(patientId).subscribe((user) => {
          this.currentUser = user;
          this.userType = user.role;
          this.userId = user.id;
          this.patchForm(user);
        })
      } else {
        this.authService.getUserLogged().subscribe(user => {
          this.currentUser = user;
          this.userId = user.id;
          this.userType = user.role;
          this.patchForm(user)
          this.disableForm()
          }
        );
      }
    });
  }

  disableForm() {
    this.parametresForm.get('nom')?.disable();
    this.parametresForm.get('prenom')?.disable();
    this.parametresForm.get('email')?.disable();
    this.parametresForm.get('telephone')?.disable();
    this.parametresForm.get('adresse')?.disable();
    this.parametresForm.get('nomParent')?.disable();
    this.parametresForm.get('prenomParent')?.disable();
    this.parametresForm.get('rpps')?.disable();
    this.parametresForm.get('siret')?.disable();
    this.parametresForm.get('photo')?.disable();
  }

  patchForm(user : any) {
    this.parametresForm.patchValue({nom : user.nom});
    this.parametresForm.get("nom")?.addValidators(Validators.required)
    this.parametresForm.patchValue({prenom : user.prenom});
    this.parametresForm.get("prenom")?.addValidators(Validators.required)
    this.parametresForm.patchValue({email : user.login});
    this.parametresForm.get("email")?.addValidators(Validators.required)
    this.parametresForm.patchValue({telephone : user.telephone});
    this.parametresForm.get("telephone")?.addValidators(Validators.required)
    switch (user.role) {
      case 'PATIENT' : {
        this.parametresForm.patchValue({adresse : user.adresse});
        this.parametresForm.get("adresse")?.addValidators(Validators.required)
        this.parametresForm.patchValue({nomParent : user.nomParent});
        this.parametresForm.get("nomParent")?.addValidators(Validators.required)
        this.parametresForm.patchValue({prenomParent : user.prenomParent});
        this.parametresForm.get("prenomParent")?.addValidators(Validators.required)
        this.parametresForm.patchValue({photo : user.photo});
        break;
      }
      case 'ORTHOPHONISTE' : {
        this.parametresForm.patchValue({adresse : user.adresse});
        this.parametresForm.get("adresse")?.addValidators(Validators.required)
        this.parametresForm.patchValue({rpps: user.rpps});
        this.parametresForm.get("rpps")?.addValidators(Validators.required)
        this.parametresForm.patchValue({siret: user.siret});
        this.parametresForm.get("siret")?.addValidators(Validators.required)
        this.parametresForm.patchValue({photo : user.photo});
        break;
      }
      case 'ADMINISTRATEUR' : {
        this.parametresForm.patchValue({nom : user.nom});
        this.parametresForm.get("nom")?.addValidators(Validators.required)
        this.parametresForm.patchValue({prenom : user.prenom});
        this.parametresForm.get("prenom")?.addValidators(Validators.required)
        break;
      }
    }
  }

  onPhotoSelected(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files || input.files.length === 0) return;

    const file = input.files[0];

    // Crée un clone isolé du fichier
    const safeFile = new Blob([file], { type: file.type });

    const reader = new FileReader();

    reader.onload = () => {
      const base64Image = reader.result as string;
      this.parametresForm.patchValue({ photo: base64Image });
    };

    reader.onerror = (error) => {
      console.error("Erreur de lecture du fichier :", error);
    };

    try {
      reader.readAsDataURL(safeFile);
    } catch (err) {
      console.error("Erreur DOMException :", err);
    }

    // Important : vider le champ input après usage
    input.value = '';
  }

  onSubmit(): void {
    if (this.parametresForm.invalid) {
      this.errorMessage = 'Veuillez remplir tous les champs obligatoires.';
      return;
    }

    this.loading = true;
    const formData = {
      ...this.parametresForm.getRawValue(), // inclut les champs désactivés
      id: this.userId,
      role : this.currentUser.role
    };

    this.userService.updateUser(formData).subscribe(
      (response) => {
        console.log(response)
        this.successMessage = "Utilisateur mis à jour avec succès";
        this.loading = false;
      })

    // switch (this.userType) {
    //   case 'PATIENT':
    //     this.patientService.updatePatient(formData).subscribe(
    //       () => {
    //         this.successMessage = 'Patient mis à jour avec succès';
    //         this.loading = false;
    //       },
    //       error => {
    //         this.errorMessage = 'Erreur lors de la mise à jour du patient';
    //         this.loading = false;
    //       }
    //     );
    //     break;
    //   case 'ORTHOPHONISTE':
    //     this.orthophonisteService.updateOrthophoniste(formData).subscribe(
    //       () => {
    //         this.successMessage = 'Orthophoniste mis à jour avec succès';
    //         this.loading = false;
    //       },
    //       error => {
    //         this.errorMessage = 'Erreur lors de la mise à jour de l’orthophoniste';
    //         this.loading = false;
    //       }
    //     );
    //     break;
    //   case 'ADMINISTRATEUR':
    //     this.administrateurService.updateAdministrateur(formData).subscribe(
    //       () => {
    //         this.successMessage = 'Administrateur mis à jour avec succès';
    //         this.loading = false;
    //       },
    //       error => {
    //         this.errorMessage = 'Erreur lors de la mise à jour de l’administrateur';
    //         this.loading = false;
    //       }
    //     );
    //     break;
    //   default:
    //     this.errorMessage = 'Rôle utilisateur non reconnu';
    //     this.loading = false;
    // }
  }

}
