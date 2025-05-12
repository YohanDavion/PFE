import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Administrateur } from '../models/administrateur.model';

@Injectable({
  providedIn: 'root'
})
export class AdministrateurService {
  private apiUrl = 'http://localhost:8080/api/administrateur';

  constructor(private http: HttpClient) { }

  getAdministrateur(id: number): Observable<Administrateur> {
    return this.http.get<Administrateur>(`${this.apiUrl}/${id}`);
  }

  getAllAdministrateurs(): Observable<Administrateur[]> {
    return this.http.get<Administrateur[]>(`${this.apiUrl}/all`);
  }

  createAdministrateur(administrateur: Administrateur): Observable<Administrateur> {
    return this.http.post<Administrateur>(this.apiUrl, administrateur);
  }

  updateAdministrateur(administrateur: Administrateur): Observable<Administrateur> {
    return this.http.put<Administrateur>(`${this.apiUrl}/${administrateur.id}`, administrateur);
  }

  deleteAdministrateur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
} 