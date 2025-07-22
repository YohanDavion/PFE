import {Component} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {DataViewModule} from 'primeng/dataview';
import {Patient} from '../interfaces/patient';
import {TagModule} from 'primeng/tag';
import {CommonModule} from '@angular/common';
import {ToastModule} from 'primeng/toast';
import {Router} from '@angular/router';
import {ConfirmationService, MessageService} from 'primeng/api';
import {TableModule} from 'primeng/table';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {PatientService} from '../services/patient.service';
import {Badge} from 'primeng/badge';

@Component({
  selector: 'app-list-patients',
  imports: [DataViewModule,ButtonModule,TagModule,CommonModule,ToastModule,TableModule,ConfirmDialogModule, Badge],
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
    this.patientService.getAllPatients().subscribe(patients => this.patients = patients);
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Suppression du Client' });
  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Echec', detail: 'Echec de la suppression du Client' });
  }

  editPatient(patient: Patient) {
    this.router.navigate(['/settings'], { queryParams: { patientId: patient.id } });
  }

  deleteOrDeactivate(patient: Patient) {
    if (patient.actif) {
      patient.actif = false;
      this.patientService.updatePatient(patient).subscribe(() => {
        this.messageService.add({severity: 'warn', summary: 'Désactivée', detail: 'Patient désactivé'});
      });
    } else {
      this.confirmationService.confirm({
        message: 'Voulez-vous vraiment supprimer cette série ?',
        acceptLabel: 'Oui',
        rejectLabel: 'Non',
        accept: () => {
          this.patientService.deletePatient(patient.id).subscribe(() => {
            this.patients = this.patients.filter(p => p.id !== patient.id);
            this.messageService.add({severity: 'success', summary: 'Supprimée', detail: 'Patient supprimé'});
          });
        }
      });
    }
  }
}
