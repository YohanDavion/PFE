import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://127.0.0.1';

  constructor(private http: HttpClient) {}

  login(login: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { 'login':login, 'password':password },{ withCredentials: true})
      .pipe(
        tap(response => {
         return response;
        })
      );
  }

  logout(): Observable<any> {
    return this.http.post(`${this.apiUrl}/logout`, {}, { withCredentials: true });
  }

  refreshToken(): Observable<any> {
    return this.http.post(`${this.apiUrl}/refresh-token`, {}, { withCredentials: true });
  }
}
