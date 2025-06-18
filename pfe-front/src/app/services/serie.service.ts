import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Serie} from '../interfaces/serie';
import {Animation} from '../models/animation.model';

@Injectable({
  providedIn: 'root'
})
export class SerieService {
  private apiUrl = 'http://localhost:8080/api/serie';

  constructor(private http: HttpClient) { }

  getSerie(id: number): Observable<Serie> {
    return this.http.get<Serie>(`${this.apiUrl}/${id}`);
  }

  getAllSeries(): Observable<Serie[]> {
    return this.http.get<Serie[]>(`${this.apiUrl}/all`);
  }

  getAllSerieByPatient(): Observable<Serie[]> {
    return this.http.get<Serie[]>(`${this.apiUrl}/all-patient`);
  }

  createSerie(serie: {}): Observable<Serie> {
    return this.http.post<Serie>(this.apiUrl, serie);
  }

  updateSerie(id : number, serie: {}): Observable<Serie> {
    return this.http.put<Serie>(`${this.apiUrl}/${id}`, serie);
  }

  deleteSerie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getSeriesByUser(): Observable<Serie[]> {
    return this.http.get<Serie[]>(`${this.apiUrl}/user`);
  }

  getAnimationsBySerie(serieId: number): Observable<Animation[]> {
    return this.http.get<Animation[]>(`${this.apiUrl}/user/${serieId}/animations`);
  }

  validateSerie(serieId : number) {
    return this.http.get<Serie>(`${this.apiUrl}/${serieId}/validate`);
  }
}
