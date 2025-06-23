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
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      adresse: [''],
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
          console.log(user);
          this.userId = user.id;
        })
      } else {
        this.authService.getUserLogged().subscribe(user => {
          this.currentUser = user;
          this.userId = user.id;
          this.userType = user.role;
          this.parametresForm.get('nom')?.disable();
          this.parametresForm.get('prenom')?.disable();
          this.parametresForm.get('email')?.disable();
          this.parametresForm.get('telephone')?.disable();
          this.parametresForm.get('adresse')?.disable();
          }
        );
      }
    });
  }

  onSubmit(): void {
    if (this.parametresForm.valid) {
      this.loading = true;
      const formData = {
        ...this.parametresForm.getRawValue(), // Utilise getRawValue() pour inclure les champs désactivés
        id: this.userId
      };

      switch (this.userType) {
        case 'PATIENT':
          this.patientService.updatePatient(formData).subscribe(
            () => {
              this.successMessage = 'Informations mises à jour avec succès';
              this.loading = false;
            },
            error => {
              this.errorMessage = 'Erreur lors de la mise à jour';
              this.loading = false;
            }
          );
          break;
        case 'ORTHOPHONISTE':
          this.orthophonisteService.updateOrthophoniste(formData).subscribe(
            () => {
              this.successMessage = 'Informations mises à jour avec succès';
              this.loading = false;
            },
            error => {
              this.errorMessage = 'Erreur lors de la mise à jour';
              this.loading = false;
            }
          );
          break;
        case 'ADMIN':
          this.administrateurService.updateAdministrateur(formData).subscribe(
            () => {
              this.successMessage = 'Informations mises à jour avec succès';
              this.loading = false;
            },
            error => {
              this.errorMessage = 'Erreur lors de la mise à jour';
              this.loading = false;
            }
          );
          break;
      }
    }
  }
}
