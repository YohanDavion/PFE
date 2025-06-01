import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PatientService } from '../services/patient.service';
import { OrthophonisteService } from '../services/orthophoniste.service';
import { AdministrateurService } from '../services/administrateur.service';
import { AuthService } from '../services/auth.service';
import { CommonModule } from '@angular/common';

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

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private orthophonisteService: OrthophonisteService,
    private administrateurService: AdministrateurService,
    private authService: AuthService
  ) {
    this.parametresForm = this.fb.group({
      nom: [{value: '', disabled: true}, Validators.required],
      prenom: [{value: '', disabled: true}, Validators.required],
      email: [{value: '', disabled: true}, [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      adresse: [''],
      dateNaissance: [''],
      numeroAdeli: ['']
    });
  }

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData(): void {
    const user = this.authService.getCurrentUser();
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