import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {OrthophonisteService} from '../services/orthophoniste.service';
import {MessageService} from 'primeng/api';
import {ToastModule} from 'primeng/toast';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {Orthophoniste} from '../models/orthophoniste.model';

@Component({
  selector: 'app-create-orthophoniste',
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
  templateUrl: './create-orthophoniste.component.html',
  styleUrls: ['./create-orthophoniste.component.scss']
})
export class CreateOrthophonisteComponent implements OnInit {
  orthophonisteForm: FormGroup;
  loading: boolean = false;
  isEditMode: boolean = false;
  orthophonisteId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private orthophonisteService: OrthophonisteService,
    private router: Router,
    private route: ActivatedRoute,
    private messageService: MessageService
  ) {
    this.orthophonisteForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      login: ['', [Validators.required, Validators.email]],
      password : ['', Validators.required],
      telephone: ['', Validators.required],
      adresse: [''],
      rpps : ['', Validators.required],
      siret : ['', Validators.required],
      actif : [false]
    });
  }

  ngOnInit(): void {
    this.orthophonisteId = Number(this.route.snapshot.queryParams['orthophonisteId']);

    if (this.orthophonisteId) {
      this.isEditMode = true;
      this.loadOrthophoniste();
    }
  }

  loadOrthophoniste(): void {
    if (this.orthophonisteId) {
      this.orthophonisteService.getOrthophoniste(this.orthophonisteId).subscribe({
        next: (orthophoniste) => {
          this.orthophonisteForm.patchValue({
            nom: orthophoniste.nom,
            prenom: orthophoniste.prenom,
            login: orthophoniste.login,
            telephone: orthophoniste.telephone,
            adresse: orthophoniste.adresse,
            actif : orthophoniste.actif,
            rpps : orthophoniste.rpps,
            siret : orthophoniste.siret
          });
        },
        error: (error) => {
          console.error('Erreur lors du chargement de l\'orthophoniste:', error);
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Erreur lors du chargement de l\'orthophoniste'
          });
        }
      });
    }
  }

  onSubmit(): void {
    if (this.orthophonisteForm.valid) {
      this.loading = true;
      const orthophonisteData = this.orthophonisteForm.value;

      if (this.isEditMode && this.orthophonisteId) {
        // Mode édition
        const orthophonisteToUpdate: Orthophoniste = {
          ...orthophonisteData,
          id: this.orthophonisteId,
        };

        this.orthophonisteService.updateOrthophoniste(orthophonisteToUpdate).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Succès',
              detail: 'Orthophoniste modifié avec succès'
            });
            this.router.navigate(['/list-orthophonistes']);
          },
          error: (error) => {
            console.error('Erreur lors de la modification de l\'orthophoniste:', error);
            this.messageService.add({
              severity: 'error',
              summary: 'Erreur',
              detail: 'Erreur lors de la modification de l\'orthophoniste'
            });
            this.loading = false;
          }
        });
      } else {
        // Mode création
        this.orthophonisteService.createOrthophoniste(orthophonisteData).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Succès',
              detail: 'Orthophoniste créé avec succès'
            });
            this.router.navigate(['/list-orthophonistes']);
          },
          error: (error) => {
            console.error('Erreur lors de la création de l\'orthophoniste:', error);
            this.messageService.add({
              severity: 'error',
              summary: 'Erreur',
              detail: 'Erreur lors de la création de l\'orthophoniste'
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

  cancel(): void {
    this.router.navigate(['/list-orthophonistes']);
  }
}
