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
import {Ripple} from 'primeng/ripple';
import {MessageService} from 'primeng/api';
import {Toast} from 'primeng/toast';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [PanelModule, InputTextModule, ButtonModule, ReactiveFormsModule, CheckboxModule, StyleClassModule, Ripple, Toast],
  styleUrls: ['./login.component.scss'],
  providers : [MessageService]
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;

    constructor(
      private fb: FormBuilder,
      private authService: AuthService,
      private router: Router,
      private messageService : MessageService,
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
            case 'PATIENT': {
              window.location.href = response.abonnementOk ? '/list-series-patient' : '/abonnements';
              break;
            }
            case 'ORTHOPHONISTE':
            case 'ADMINISTRATEUR': {
              window.location.href = '/list-patients';
              break;
            }
          }
        },
        error: (error) => {
          this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Identifiants incorrects' });
        }
      });
    }
  }
  goToInscription(): void {
    this.router.navigate(['/inscription']);
  }
}
