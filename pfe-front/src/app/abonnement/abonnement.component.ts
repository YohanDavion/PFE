import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Abonnement} from '../models/abonnement.model';
import {AbonnementService} from '../services/abonnement.service';
import {CommonModule} from '@angular/common';
import {Dialog} from 'primeng/dialog';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {ButtonDirective} from 'primeng/button';
import {MessageService, PrimeTemplate} from 'primeng/api';
import {Toast} from 'primeng/toast';
import {Card} from 'primeng/card';
import {Ripple} from 'primeng/ripple';

@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrl: './abonnement.component.scss',
  providers : [MessageService],
  imports: [CommonModule, Dialog, FormsModule, InputText, ButtonDirective, PrimeTemplate, Toast, Card, Ripple],
})
export class AbonnementComponent implements OnInit{
  loading = false;
  abonnements : Abonnement[] | undefined;

  //Pop-up
  displayJoinDialog: boolean = false;
  ownerEmail: string = '';
  selectedAbonnementId: number | null = null;

  constructor(private http: HttpClient,
              private abonnementService : AbonnementService,
              private messageService: MessageService) {}

  ngOnInit(): void {
    this.abonnementService.getAllAbonnements().subscribe(abonnements => {
      this.abonnements = abonnements
    })
  }

  choisirAbonnement(abonnementId: number) {
    this.loading = true;

    this.abonnementService.checkoutSession(abonnementId).subscribe({
        next: (response) => {
          window.location.href = response.checkoutUrl
        },
        error: (err) => {
          console.error('Erreur lors de la création de la session Stripe', err);
          this.loading = false;
        }
      });
  }

  rejoindreAbonnement(abonnementId: number) {
    this.selectedAbonnementId = abonnementId;
    this.displayJoinDialog = true;
  }

  cancelJoin() {
    this.displayJoinDialog = false;
    this.ownerEmail = '';
    this.selectedAbonnementId = null;
  }

  confirmJoin() {
    if (!this.ownerEmail || !this.selectedAbonnementId) return;

    this.loading = true;
    this.abonnementService.rejoindreAbonnement(this.selectedAbonnementId, this.ownerEmail)
      .subscribe({
        next: (response) => {
          this.displayJoinDialog = false;
          this.loading = false;
          localStorage.setItem('abonnementOk', response.ok)
          this.messageService.add({
            severity: response.ok ? 'success' : 'error',
            summary: response.ok ? 'Succès' : 'Erreur',
            detail: response.message,
          });
          // window.location.href = response.checkoutUrl;
        },
        error: (err) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur serveur',
            detail: 'Une erreur est survenue lors de la tentative de rejoindre l’abonnement.',
          });
          this.loading = false;
        }
      });
  }
}
