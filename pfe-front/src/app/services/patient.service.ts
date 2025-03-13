import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../interfaces/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private apiUrl = 'http://127.0.0.1:8080/api/patient';

  constructor(private http: HttpClient) { }

 /* getAllPatients(): Patient[] {
    }*/
}
