import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {PatientService} from '../services/patient.service';
import {MessageService} from 'primeng/api';
import {ToastModule} from 'primeng/toast';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';

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
    private route : ActivatedRoute,
    private messageService: MessageService
  ) {
    this.patientForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      login: ['', [Validators.required, Validators.email]],
      password : ['', Validators.required],
      telephone: ['', Validators.required],
      adresse: [''],
      dateNaissance: [''],
      nomParent: ['', Validators.required],
      prenomParent: ['', Validators.required],
      photo : ['']
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.patientForm.valid) {
      this.loading = true;
      const patientData = this.patientForm.value;

      const mode = this.route.snapshot.data['mode'];

      if (mode == 'inscription') {
        this.patientService.inscription(patientData).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Succès',
              detail: 'Compte créé avec succès'
            });
            window.location.href = 'login';
          },
          error: (error) => {
            console.error('Erreur lors de la création du compte:', error);
            this.messageService.add({
              severity: 'error',
              summary: 'Erreur',
              detail: 'Erreur lors de la création du compte'
            });
            this.loading = false;
          }
        })
      } else {
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
      }

    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Erreur',
        detail: 'Veuillez remplir tous les champs obligatoires'
      });
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
      this.patientForm.patchValue({ photo: base64Image.split(',')[1] });
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

  cancel(): void {
    this.router.navigate(['/list-patients']);
  }
}
