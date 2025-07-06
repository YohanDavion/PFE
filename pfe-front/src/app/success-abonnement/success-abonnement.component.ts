import {Component, OnInit} from '@angular/core';
import {AbonnementService} from '../services/abonnement.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-success-abonnement',
  imports: [],
  templateUrl: './success-abonnement.component.html',
  styleUrl: './success-abonnement.component.scss'
})
export class SuccessAbonnementComponent implements OnInit {
  abonnementId : number = 0;
  constructor(public activatedRoute: ActivatedRoute,
              private abonnementService : AbonnementService,
              private router: Router) {
  }
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.abonnementId = params['abonnementId'];
      this.abonnementService.affectAbonnement(this.abonnementId).subscribe(() => {
        localStorage.setItem('abonnementOk', 'true');
        this.router.navigate(['/list-series-patient']);
      });
    });

  }
}
