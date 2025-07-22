import {Component, OnInit} from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {ConfirmDialog} from "primeng/confirmdialog";
import {ConfirmationService, MessageService, PrimeTemplate} from "primeng/api";
import {TableModule} from "primeng/table";
import {Toast} from "primeng/toast";
import {AbonnementService} from '../services/abonnement.service';
import {Badge} from 'primeng/badge';
import {AuthService} from '../services/auth.service';
import {DatePipe} from '@angular/common';
import {PatientService} from '../services/patient.service';

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
  providers : [ConfirmationService, MessageService, DatePipe],
  templateUrl: './validate-abonnement.component.html',
  styleUrl: './validate-abonnement.component.scss'
})
export class ValidateAbonnementComponent implements OnInit{
  patientsAbonnements : []  = [];
  dateExpiration : any;

  constructor(private abonnementService : AbonnementService,
              private authService : AuthService,
              private messageService : MessageService,
              private patientService : PatientService,
              private datePipe : DatePipe,
              private confirmationService : ConfirmationService) {
  }
  ngOnInit(): void {
    this.abonnementService.getPatientAbonnement().subscribe(pa => {
      this.patientsAbonnements = pa;
    });

    this.authService.getUserLogged().subscribe(user => {
      this.dateExpiration = this.datePipe.transform(new Date(Date.parse(user.dateExpirationAbonnement)), 'dd/MM/yyyy');
    })
  }

  changeStatut(patientAbonnement : any) {
    if (!patientAbonnement.valide) {
      // On intègre l'user à l'abonnement
      this.abonnementService.joinAbonnement(patientAbonnement).subscribe();
    } else {
      // On retire l'user de l'abonnement
      this.abonnementService.retrieveAbonnement(patientAbonnement).subscribe();
    }
    patientAbonnement.valide = !patientAbonnement.valide;
    // this.abonnementService.updatePatientAbonnement(patientAbonnement).subscribe();
    // patientAbonnement.patient.datePaiement = patientAbonnement.proprietaire.datePaiement;
    // this.patientService.updatePatient(patientAbonnement.patient).subscribe();
  }

}
