import { Component,NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import { Patient } from '../interfaces/patient';
import {Animation} from '../interfaces/animation';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-list-patients',
  imports: [DataViewModule,ButtonModule,TagModule,CommonModule,ToastModule,TableModule],
  providers: [MessageService],
  templateUrl: './list-patients.component.html',
  styleUrl: './list-patients.component.scss'
})
export class ListPatientsComponent {
  patients: Patient[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService
  )
    {
  }

  ngOnInit(): void {
    const mockPatients = [
      new Patient('Dupont', 'Marie', 'Dupont', 'Lucas'),
      new Patient('Martin', 'Pierre', 'Martin', 'Emma'),
      new Patient('Dubois', 'Sophie', 'Dubois', 'Noah'),
      new Patient('Lefebvre', 'Thomas', 'Lefebvre', 'Chloé'),
      new Patient('Moreau', 'Claire', 'Moreau', 'Louis'),
      new Patient('aze', 'aze', 'Dupont', 'Lucas'),
      new Patient('Martin', 'aze', 'Martin', 'Emma'),
      new Patient('Dubois', 'Sophie', 'aze', 'Noah'),
      new Patient('Lefebvre', 'Thomas', 'aze', 'Chloé'),
      new Patient('Moreau', 'Claire', 'Moreau', 'aze')
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
}