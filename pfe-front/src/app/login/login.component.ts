import { Component, OnInit } from '@angular/core';
import { Validators,FormControl,FormGroup,ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { PanelModule } from 'primeng/panel';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import {StyleClassModule} from 'primeng/styleclass';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [PanelModule,InputTextModule,ButtonModule,ReactiveFormsModule,CheckboxModule,StyleClassModule],
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
  
    constructor(
      private fb: FormBuilder,
      private authService: AuthService,
      private router: Router
    ) {
      this.loginForm = this.fb.group({
        login: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required]
      });
    }
  
    ngOnInit(): void {}
  
    onSubmit(): void {
      if (this.loginForm.valid) {
        const { login, password } = this.loginForm.value;
        this.authService.login(login, password).subscribe({
          next: (response) => {
            this.router.navigate(['/list-patient']);
          },
          error: (error) => {
            console.error('Erreur de connexion', error);
          }
        });
      }
    }
  }
  