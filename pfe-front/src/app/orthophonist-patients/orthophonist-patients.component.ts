import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient.model';
import { OrthophonisteService } from '../services/orthophoniste.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orthophonist-patients',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orthophonist-patients.component.html',
  styleUrls: ['./orthophonist-patients.component.scss']
})
export class OrthophonistPatientsComponent implements OnInit {
  patients: Patient[] = [];
  orthophonisteId!: number;
  loading = false;

  constructor(private orthophonisteService: OrthophonisteService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.orthophonisteId = +this.route.snapshot.paramMap.get('id')!;
    this.loadPatients();
  }

  loadPatients() {
    this.loading = true;
    this.orthophonisteService.getPatientsByOrthophoniste(this.orthophonisteId).subscribe(data => {
      this.patients = data;
      this.loading = false;
    });
  }
} 