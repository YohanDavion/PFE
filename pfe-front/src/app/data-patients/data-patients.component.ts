import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { MessageService } from 'primeng/api';
import { SerieService } from '../services/serie.service';
import { Serie } from '../models/serie.model';
import { PatientService } from '../services/patient.service';
import { Patient } from '../models/patient.model';

@Component({
  selector: 'app-data-patients',
  imports: [
    CommonModule, 
    FormsModule, 
    TableModule, 
    ButtonModule, 
    DropdownModule, 
    ToastModule,
    TooltipModule
  ],
  providers: [MessageService],
  templateUrl: './data-patients.component.html',
  styleUrls: ['./data-patients.component.scss']
})
export class DataPatientsComponent implements OnInit {
  patient: Patient | null = null;
  series: Serie[] = [];
  filteredSeries: Serie[] = [];
  selectedFilter: string | null = null;
  filterOptions = [
    { label: 'Toutes les séries', value: 'all' },
    { label: 'Séries terminées', value: 'completed' },
    { label: 'Séries en cours', value: 'pending' }
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private serieService: SerieService,
    private patientService: PatientService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    const patientId = this.route.snapshot.queryParams['patientId'];
    if (patientId) {
      this.loadPatientData(patientId);
      this.loadPatientSeries(patientId);
    }
  }

  private loadPatientData(patientId: number) {
    this.patientService.getPatient(patientId).subscribe({
      next: (patient) => {
        this.patient = patient;
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Impossible de charger les données du patient'
        });
      }
    });
  }

  private loadPatientSeries(patientId: number) {
    this.serieService.getSeriesByPatient(patientId).subscribe({
      next: (series) => {
        this.series = series;
        this.filteredSeries = series;
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Impossible de charger les séries du patient'
        });
      }
    });
  }

  filterSeries() {
    if (!this.selectedFilter || this.selectedFilter === 'all') {
      this.filteredSeries = this.series;
    } else {
      this.filteredSeries = this.series.filter(serie => 
        this.selectedFilter === 'completed' ? serie.active === 'INACTIF' : serie.active === 'ACTIF'
      );
    }
  }

  viewSerieDetails(serie: Serie) {
    // TODO: Implémenter la navigation vers les détails de la série
    console.log('Voir les détails de la série:', serie);
  }

  startSerie(serie: Serie) {
    this.router.navigate(['/list-animations-patient'], { 
      queryParams: { 
        serieId: serie.id,
        patientId: this.patient?.id 
      }
    });
  }

  goBack() {
    this.router.navigate(['/list-patients']);
  }
} 