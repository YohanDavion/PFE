import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {PanelModule} from 'primeng/panel';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {CheckboxModule} from 'primeng/checkbox';
import {StyleClassModule} from 'primeng/styleclass';
import {AuthService} from '../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [PanelModule,InputTextModule,ButtonModule,ReactiveFormsModule,CheckboxModule,StyleClassModule],
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    greeting = {};

    constructor(
      private fb: FormBuilder,
      private authService: AuthService,
      private router: Router,
      private http: HttpClient
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
            switch (response.role) {
              case 'PATIENT' : {
                if (response.abonnementOk) {
                  this.router.navigate(['/list-series-patient']);
                } else {
                  this.router.navigate(['/abonnements'])
                }
                break;
              }
              case 'ORTHOPHONISTE' : {
                this.router.navigate(['/list-patients']);
                break;
              }
              case 'ADMINISTRATEUR' : {
                this.router.navigate(['/list-patients']);
                break;
              }
            }
          },
          error: (error) => {
            console.error('Erreur de connexion', error);
          }
        });
      }
    }
  }
