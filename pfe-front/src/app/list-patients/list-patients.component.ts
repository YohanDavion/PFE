import { Component,NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import { Patient } from '../interfaces/patient';
import {Animation} from '../interfaces/animation';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { Router } from '@angular/router';
import { MessageService, ConfirmationService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-list-patients',
  imports: [DataViewModule,ButtonModule,TagModule,CommonModule,ToastModule,TableModule,ConfirmDialogModule],
  providers: [MessageService, ConfirmationService, PatientService],
  templateUrl: './list-patients.component.html',
  styleUrl: './list-patients.component.scss'
})
export class ListPatientsComponent {
  patients: Patient[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private patientService: PatientService
  )
    {
  }

  ngOnInit(): void {
    const mockPatients = [
      new Patient(1, 'Dupont', 'Marie', 'Dupont', 'Lucas'),
      new Patient(2, 'Martin', 'Pierre', 'Martin', 'Emma'),
      new Patient(3, 'Dubois', 'Sophie', 'Dubois', 'Noah'),
      new Patient(4, 'Lefebvre', 'Thomas', 'Lefebvre', 'Chloé'),
      new Patient(5, 'Moreau', 'Claire', 'Moreau', 'Louis'),
      new Patient(6, 'aze', 'aze', 'Dupont', 'Lucas'),
      new Patient(7, 'Martin', 'aze', 'Martin', 'Emma'),
      new Patient(8, 'Dubois', 'Sophie', 'aze', 'Noah'),
      new Patient(9, 'Lefebvre', 'Thomas', 'aze', 'Chloé'),
      new Patient(10, 'Moreau', 'Claire', 'Moreau', 'aze')
    ];
    this.patients = mockPatients;
  }

  test(){

  } 

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Succés', detail: 'Suppréssion du Client' });
  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Echec', detail: 'Echec de la suppréssion du Client' });
}

editPatient(patient: Patient) {
  this.router.navigate(['/settings'], { queryParams: { patientId: patient.id } });
}

deletePatient(patient: Patient) {
  this.confirmationService.confirm({
    message: 'Êtes-vous sûr de vouloir supprimer ce patient ?',
    header: 'Confirmation de suppression',
    icon: 'pi pi-exclamation-triangle',
    acceptLabel: 'Oui',
    rejectLabel: 'Non',
    accept: () => {
      this.patientService.deletePatient(patient.id).subscribe({
        next: () => {
          this.patients = this.patients.filter(p => p.id !== patient.id);
          this.showSuccess();
        },
        error: (error) => {
          console.error('Erreur lors de la suppression:', error);
          this.showError();
        }
      });
    }
  });
}
}