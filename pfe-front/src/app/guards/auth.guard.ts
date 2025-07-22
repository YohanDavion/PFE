import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {isPlatformBrowser} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              @Inject(PLATFORM_ID) private platformId: Object) {
  }

  canActivate(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('jwt_token');
      if (token) {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      // Si nous sommes côté serveur, on peut rediriger par défaut ou gérer autrement
      // Pour un AuthGuard, le plus sûr est de rediriger pour éviter d'afficher du contenu non autorisé
      this.router.navigate(['/login']);
      return false;
    }

  }
}
