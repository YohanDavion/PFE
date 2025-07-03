import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    const userRole = localStorage.getItem('user_role'); // Tu dois stocker le rôle au login

    if (userRole && expectedRoles.includes(userRole.toUpperCase())) {
      return true;
    } else {
      this.router.navigate(['/']); // Crée cette page si tu veux
      return false;
    }
  }
}
