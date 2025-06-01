import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-create-patient',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ToastModule,
    InputTextModule,
    ButtonModule,
    CardModule
  ],
  providers: [MessageService],
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.scss']
})
export class CreatePatientComponent implements OnInit {
  patientForm: FormGroup;
  loading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private router: Router,
    private messageService: MessageService
  ) {
    this.patientForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      adresse: [''],
      dateNaissance: [''],
      nomParent: ['', Validators.required],
      prenomParent: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.patientForm.valid) {
      this.loading = true;
      const patientData = this.patientForm.value;

      this.patientService.createPatient(patientData).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Succès',
            detail: 'Patient créé avec succès'
          });
          this.router.navigate(['/list-patients']);
        },
        error: (error) => {
          console.error('Erreur lors de la création du patient:', error);
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Erreur lors de la création du patient'
          });
          this.loading = false;
        }
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Erreur',
        detail: 'Veuillez remplir tous les champs obligatoires'
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/list-patients']);
  }
} 