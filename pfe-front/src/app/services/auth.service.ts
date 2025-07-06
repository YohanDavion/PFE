import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';

interface User {
  id: number;
  role: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  constructor(private http: HttpClient) {}

  login(login: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { login, password })
      .pipe(
        tap(response => {
          if (typeof window !== 'undefined') {
            localStorage.setItem('jwt_token', response.token);
            localStorage.setItem('user_role', response.role);
            localStorage.setItem('abonnementOk', response.abonnementOk);
          }
        })
      );
  }

  logout(): void {
    if (typeof window !== 'undefined') {
      localStorage.removeItem('jwt_token');
    }
  }

  getToken(): string | null {
    if (typeof window !== 'undefined') {
      return localStorage.getItem('jwt_token');
    }
    return null;
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  getUserLogged() : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user`);
  }

  refreshToken(): Observable<any> {
    return this.http.post(`${this.apiUrl}/refresh-token`, {}, { withCredentials: true });
  }
}
