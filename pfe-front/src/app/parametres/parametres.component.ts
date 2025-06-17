import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {PatientService} from '../services/patient.service';
import {OrthophonisteService} from '../services/orthophoniste.service';
import {AdministrateurService} from '../services/administrateur.service';
import {AuthService} from '../services/auth.service';
import {CommonModule} from '@angular/common';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-parametres',
  templateUrl: './parametres.component.html',
  styleUrls: ['./parametres.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class ParametresComponent implements OnInit {
  parametresForm: FormGroup;
  userType: string = '';
  userId: number = 0;
  loading: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';
  isEditingPatient: boolean = false;

  constructor(
    private fb: FormBuilder,
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
      dateNaissance: [''],
      numeroAdeli: ['']
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const patientId = params['patientId'];
      if (patientId) {
        this.isEditingPatient = true;
        this.userType = 'PATIENT';
        this.userId = patientId;
        this.loadPatientData(patientId);
      } else {
        this.parametresForm.get('nom')?.disable();
        this.parametresForm.get('prenom')?.disable();
        this.parametresForm.get('email')?.disable();
        this.loadUserData();
      }
    });
  }

  loadPatientData(patientId: number): void {
    this.patientService.getPatient(patientId).subscribe(
      patient => {
        this.parametresForm.patchValue({
          nom: patient.nom,
          prenom: patient.prenom,
          email: patient.email,
          telephone: patient.telephone,
          adresse: patient.adresse,
          dateNaissance: patient.dateNaissance
        });
      },
      error => {
        this.errorMessage = 'Erreur lors du chargement des données du patient';
      }
    );
  }

  loadUserData(): void {
    // const user = this.authService.getCurrentUser();
    let user = {
      id:0,
      role:"type"
    }
    if (user) {
      this.userType = user.role;
      this.userId = user.id;

      switch (this.userType) {
        case 'PATIENT':
          this.patientService.getPatient(this.userId).subscribe(
            patient => {
              this.parametresForm.patchValue({
                nom: patient.nom,
                prenom: patient.prenom,
                email: patient.email,
                telephone: patient.telephone,
                adresse: patient.adresse,
                dateNaissance: patient.dateNaissance
              });
            }
          );
          break;
        case 'ORTHOPHONISTE':
          this.orthophonisteService.getOrthophoniste(this.userId).subscribe(
            orthophoniste => {
              this.parametresForm.patchValue({
                nom: orthophoniste.nom,
                prenom: orthophoniste.prenom,
                email: orthophoniste.email,
                telephone: orthophoniste.telephone,
                adresse: orthophoniste.adresse,
                numeroAdeli: orthophoniste.numeroAdeli
              });
            }
          );
          break;
        case 'ADMIN':
          this.administrateurService.getAdministrateur(this.userId).subscribe(
            admin => {
              this.parametresForm.patchValue({
                nom: admin.nom,
                prenom: admin.prenom,
                email: admin.email,
                telephone: admin.telephone
              });
            }
          );
          break;
      }
    }
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
