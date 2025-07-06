import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    const userRole = localStorage.getItem('user_role');
    const abonnementOk = localStorage.getItem('abonnementOk');

    if (userRole && expectedRoles.includes(userRole.toUpperCase())) {
      if (userRole == 'PATIENT' && abonnementOk != 'true') {
        this.router.navigate(['/abonnements']);
        return false;
      }
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
