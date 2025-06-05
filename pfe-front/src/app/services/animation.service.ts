import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Animation} from '../interfaces/animation';

// import {Animation} from '../models/animation.model';

@Injectable({
  providedIn: 'root'
})
export class AnimationService {
  private apiUrl = 'http://localhost:8080/api/animation';

  constructor(private http: HttpClient) { }

  getAnimation(id: number): Observable<Animation> {
    return this.http.get<Animation>(`${this.apiUrl}/${id}`);
  }

  getAllAnimations(): Observable<Animation[]> {
    return this.http.get<Animation[]>(`${this.apiUrl}/all`);
  }

  createAnimation(animation: Animation): Observable<Animation> {
    return this.http.post<Animation>(this.apiUrl, animation);
  }

  createAnimationFormData(formData : FormData) : Observable<Animation> {
    return this.http.post<Animation>(this.apiUrl, formData)
  }
  updateAnimation(animation: Animation): Observable<Animation> {
    return this.http.put<Animation>(`${this.apiUrl}/${animation.id}`, animation);
  }

  deleteAnimation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getAnimationBySerie(serieId:number) : Observable<Animation[]> {
    return this.http.get<Animation[]>(`${this.apiUrl}/serie/${serieId}`);
  }
}
