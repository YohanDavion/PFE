import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient.model';
import { PatientService } from '../services/patient.service';
import { OrthophonisteService } from '../services/orthophoniste.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { MultiSelectModule } from 'primeng/multiselect';

@Component({
  selector: 'app-assign-patients',
  standalone: true,
  imports: [CommonModule, FormsModule, ButtonModule, MultiSelectModule],
  templateUrl: './assign-patients.component.html',
  styleUrls: ['./assign-patients.component.scss']
})
export class AssignPatientsComponent implements OnInit {
  patients: Patient[] = [];
  selectedPatients: Patient[] = [];
  orthophonisteId!: number;
  loading = false;
  error: string | null = null;

  constructor(
    private patientService: PatientService,
    private orthophonisteService: OrthophonisteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.orthophonisteId = +this.route.snapshot.paramMap.get('id')!;
    this.loadPatients();
  }

  loadPatients() {
    this.patientService.getAllPatients().subscribe((data: any) => {
      // On ne propose que les patients non affectés
      this.patients = data.filter((p: any) => !p.orthophonisteId).map((patient: any) => ({
        ...patient,
        email: patient.email || '',
        dateNaissance: patient.dateNaissance || new Date()
      }));
    });
  }

  assign() {
    if (!this.selectedPatients.length) return;
    this.loading = true;
    this.orthophonisteService.assignPatientsToOrthophoniste(
      this.orthophonisteId,
      this.selectedPatients.map(p => p.id)
    ).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/orthophonist-patients', this.orthophonisteId]);
      },
      error: () => {
        this.loading = false;
        this.error = 'Erreur lors de l\'affectation';
      }
    });
  }
} 