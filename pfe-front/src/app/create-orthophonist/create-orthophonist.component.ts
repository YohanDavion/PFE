import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Orthophoniste } from '../models/orthophoniste.model';
import { OrthophonisteService } from '../services/orthophoniste.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-create-orthophonist',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ButtonModule, InputTextModule],
  templateUrl: './create-orthophonist.component.html',
  styleUrls: ['./create-orthophonist.component.scss']
})
export class CreateOrthophonistComponent {
  @Output() created = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  form: FormGroup;
  loading = false;
  error: string | null = null;

  constructor(private fb: FormBuilder, private orthophonisteService: OrthophonisteService) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      adresse: ['', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) return;
    this.loading = true;
    this.orthophonisteService.createOrthophoniste(this.form.value as Orthophoniste).subscribe({
      next: () => {
        this.loading = false;
        this.created.emit();
      },
      error: err => {
        this.loading = false;
        this.error = 'Erreur lors de la création';
      }
    });
  }

  cancel() {
    this.close.emit();
  }
} 