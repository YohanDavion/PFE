import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orthophoniste } from '../models/orthophoniste.model';

@Injectable({
  providedIn: 'root'
})
export class OrthophonisteService {
  private apiUrl = 'http://localhost:8080/api/orthophoniste';

  constructor(private http: HttpClient) { }

  getOrthophoniste(id: number): Observable<Orthophoniste> {
    return this.http.get<Orthophoniste>(`${this.apiUrl}/${id}`);
  }

  getAllOrthophonistes(): Observable<Orthophoniste[]> {
    return this.http.get<Orthophoniste[]>(`${this.apiUrl}/all`);
  }

  createOrthophoniste(orthophoniste: Orthophoniste): Observable<Orthophoniste> {
    return this.http.post<Orthophoniste>(this.apiUrl, orthophoniste);
  }

  updateOrthophoniste(orthophoniste: Orthophoniste): Observable<Orthophoniste> {
    return this.http.put<Orthophoniste>(`${this.apiUrl}/${orthophoniste.id}`, orthophoniste);
  }

  deleteOrthophoniste(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
} 