import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { Patient } from '../models/patient.model';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { ToastModule } from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    CardModule,
    ToastModule,
    CalendarModule
  ],
  providers: [MessageService],
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {
  patientForm!: FormGroup;
  patientId!: number;
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private patientService: PatientService,
    private fb: FormBuilder,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.patientId = +this.route.snapshot.paramMap.get('id')!;
    this.initForm();
    this.loadPatient();
  }

  initForm(): void {
    this.patientForm = this.fb.group({
      id: [null],
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      dateNaissance: [null, Validators.required],
      adresse: ['', Validators.required]
    });
  }

  loadPatient(): void {
    this.isLoading = true;
    this.patientService.getPatient(this.patientId).subscribe({
      next: (patient) => {
        this.patientForm.patchValue({
          ...patient,
          dateNaissance: new Date(patient.dateNaissance)
        });
        this.isLoading = false;
      },
      error: (error) => {
        this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Impossible de charger les données du patient' });
        this.isLoading = false;
      }
    });
  }

  savePatient(): void {
    if (this.patientForm.valid) {
      const patient: Patient = this.patientForm.value;
      this.patientService.updatePatient(patient).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Patient mis à jour avec succès' });
          setTimeout(() => this.router.navigate(['/list-patients']), 1500);
        },
        error: (error) => {
          this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Échec de la mise à jour du patient' });
        }
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Attention', detail: 'Veuillez remplir tous les champs obligatoires' });
    }
  }

  cancel(): void {
    this.router.navigate(['/list-patients']);
  }
} 