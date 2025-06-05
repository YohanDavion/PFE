import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {CardModule} from 'primeng/card';
import {CommonModule} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import {AnimationService} from '../services/animation.service';

@Component({
  selector: 'app-create-animation',
  imports: [
    InputTextModule,
    FormsModule,
    CommonModule,
    CardModule,
    ReactiveFormsModule,
  ],
  templateUrl: './create-animation.component.html',
  standalone: true,
  styleUrl: './create-animation.component.scss'
})
export class CreateAnimationComponent {
  form: FormGroup;

  // Fichiers sélectionnés
  files: { [key: string]: File } = {};

  constructor(private fb: FormBuilder,
              private http: HttpClient,
              private animationService: AnimationService) {
    this.form = this.fb.group({
      libelle: ['', Validators.required]
    });
  }

  onFileSelected(event: any, field: string) {
    const file = event.target.files[0];
    if (file) {
      this.files[field] = file;
    }
  }

  submitForm() {
    if (this.form.invalid || !this.files['gif'] || !this.files['dessin'] || !this.files['son']) {
      console.log('Formulaire invalide');
      return;
    }

    const formData = new FormData();
    formData.append('libelle', this.form.get('libelle')?.value);

    if (this.files['gif']) {
      formData.append('gif', this.files['gif']);
    }

    if (this.files['dessin']) {
      formData.append('dessin', this.files['dessin']);
    }

    if (this.files['son']) {
      formData.append('son', this.files['son']);
    }

    this.animationService.createAnimationFormData(formData).subscribe(animation => console.log(animation));

  }
}
