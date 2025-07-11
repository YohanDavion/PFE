import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Abonnement} from '../models/abonnement.model';

@Injectable({
  providedIn: 'root'
})
export class AbonnementService {
  private apiUrl = 'http://localhost:8080/api/abonnement';

  constructor(private http: HttpClient) { }

  getAbonnement(id: number): Observable<Abonnement> {
    return this.http.get<Abonnement>(`${this.apiUrl}/${id}`);
  }

  getAllAbonnements(): Observable<Abonnement[]> {
    return this.http.get<Abonnement[]>(`${this.apiUrl}/all`);
  }

  createAbonnement(abonnement: Abonnement): Observable<Abonnement> {
    return this.http.post<Abonnement>(this.apiUrl, abonnement);
  }

  updateAbonnement(abonnement: Abonnement): Observable<Abonnement> {
    return this.http.put<Abonnement>(`${this.apiUrl}/${abonnement.id}`, abonnement);
  }

  deleteAbonnement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  checkoutSession(id : number) : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/checkout/${id}`)
  }

  affectAbonnement(abonnementId : number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/affectAbonnement?abonnementId=${abonnementId}`)
  }

  rejoindreAbonnement(abonnementId: number, ownerEmail: string) {
    return this.http.get<any>(`${this.apiUrl}/rejoindreAbonnement?abonnementId=${abonnementId}&mail=${ownerEmail}`)
  }
}
