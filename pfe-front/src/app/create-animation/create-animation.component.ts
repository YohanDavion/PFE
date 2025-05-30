import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {CardModule} from 'primeng/card';
import {CommonModule} from '@angular/common';

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

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      libelle: ['', Validators.required],
      gif: ['', [Validators.required]],
      dessin: ['', Validators.required],
      son: ['', Validators.required],
    });
  }
  ngOnInit(): void {
  }

  // Soumission du formulaire
  submitForm() {
    console.log(this.form.value);
  }
}
