import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-create-user',
  imports: [
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    CardModule,
    ToastModule,
    CommonModule,
    DropdownModule
  ],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.scss'
})
export class CreateUserComponent implements OnInit {

  inscriptionForm: FormGroup;
  roles = ['Patient', 'Ortho', 'Admin'];

  constructor(private fb: FormBuilder) {
    this.inscriptionForm = this.fb.group({
      role: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      parentPrenom: [''],
      parentNom: ['']
    });
  }

  ngOnInit(): void {
  }

  // Fonction pour valider si le champ est requis en fonction du rôle
  getFormValidation(role: string, field: string) {
    if (role === 'Patient' && (field === 'parentPrenom' || field === 'parentNom')) {
      return Validators.required;
    } else {
      return null;
    }
  }

  // Fonction pour afficher ou masquer les champs parent en fonction du rôle
  showParentFields(role: string) {
    if (role === 'Patient') {
      this.inscriptionForm.get('parentPrenom')?.setValidators([Validators.required]);
      this.inscriptionForm.get('parentNom')?.setValidators([Validators.required]);
      this.inscriptionForm.get('parentPrenom')?.updateValueAndValidity();
      this.inscriptionForm.get('parentNom')?.updateValueAndValidity();
    } else {
      this.inscriptionForm.get('parentPrenom')?.clearValidators();
      this.inscriptionForm.get('parentNom')?.clearValidators();
      this.inscriptionForm.get('parentPrenom')?.updateValueAndValidity();
      this.inscriptionForm.get('parentNom')?.updateValueAndValidity();
    }
  }

  // Fonction pour gérer le changement de rôle
  onRoleChange() {
    const selectedRole = this.inscriptionForm.get('role')?.value;
    this.showParentFields(selectedRole);
  }

  // Soumission du formulaire
  submitForm() {
    console.log(this.inscriptionForm.value);
  }

}
