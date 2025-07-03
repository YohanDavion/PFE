import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Abonnement} from '../models/abonnement.model';
import {AbonnementService} from '../services/abonnement.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrl: './abonnement.component.scss',
  imports: [CommonModule],
})
export class AbonnementComponent implements OnInit{
  loading = false;
  abonnements : Abonnement[] | undefined;

  constructor(private http: HttpClient, private abonnementService : AbonnementService) {}

  ngOnInit(): void {
    this.abonnementService.getAllAbonnements().subscribe(abonnements => this.abonnements = abonnements)
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
}
