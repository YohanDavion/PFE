import {Component, OnInit} from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {ConfirmDialog} from "primeng/confirmdialog";
import {ConfirmationService, MessageService, PrimeTemplate} from "primeng/api";
import {TableModule} from "primeng/table";
import {Toast} from "primeng/toast";
import {AbonnementService} from '../services/abonnement.service';
import {Badge} from 'primeng/badge';

@Component({
  selector: 'app-validate-abonnement',
  imports: [
    ButtonDirective,
    ConfirmDialog,
    PrimeTemplate,
    TableModule,
    Toast,
    Badge
  ],
  providers : [ConfirmationService, MessageService],
  templateUrl: './validate-abonnement.component.html',
  styleUrl: './validate-abonnement.component.scss'
})
export class ValidateAbonnementComponent implements OnInit{
  patientsAbonnements : []  = [];

  constructor(private abonnementService : AbonnementService,
              private messageService : MessageService,
              private confirmationService : ConfirmationService) {
  }
  ngOnInit(): void {
    this.abonnementService.getPatientAbonnement().subscribe(pa => {
      this.patientsAbonnements = pa;
    })
  }

  changeStatut(patientAbonnement : any) {
    patientAbonnement.valide = !patientAbonnement.valide;
    this.abonnementService.updatePatientAbonnement(patientAbonnement).subscribe();
  }

}
