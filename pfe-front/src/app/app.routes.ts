import {Routes} from '@angular/router';
import {ListSeriesComponent} from './list-series/list-series.component';
import {ListPatientsComponent} from './list-patients/list-patients.component';
import {ListOrthophonistesComponent} from './list-orthophonistes/list-orthophonistes.component';
import {LoginComponent} from './login/login.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {ListSeriesPatientComponent} from './list-series-patient/list-series-patient.component';
import {ListAnimationsPatientComponent} from './list-animations-patient/list-animations-patient.component';
import {CreateAnimationComponent} from './create-animation/create-animation.component';
import {ParametresComponent} from './parametres/parametres.component';
import {CreatePatientComponent} from './create-patient/create-patient.component';
import {CreateOrthophonisteComponent} from './create-orthophoniste/create-orthophoniste.component';
import {CreateSerieComponent} from './create-series/create-series.component';
import {ListAnimationsComponent} from './list-animations/list-animations.component';
import {EditAnimationComponent} from './edit-animation/edit-animation.component';
import {EditSerieComponent} from './edit-serie/edit-serie.component';
import {AuthGuard} from './guards/auth.guard';
import {RoleGuard} from './guards/role.guard';
import {AbonnementComponent} from './abonnement/abonnement.component';
import {SuccessAbonnementComponent} from './success-abonnement/success-abonnement.component';

export const routes: Routes = [
  { path: '', redirectTo : 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'success', component: SuccessAbonnementComponent },
  { path: 'inscription', component: CreatePatientComponent, data: { mode: 'inscription'}},

  // Routes accessibles uniquement si connecté
  { path: 'list-series', component: ListSeriesComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'list-animations', component: ListAnimationsComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'list-patients', component: ListPatientsComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'list-orthophonistes', component: ListOrthophonistesComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ADMINISTRATEUR'] } },
  { path: 'create-user', component: CreateUserComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ADMINISTRATEUR'] } },
  { path: 'create-orthophoniste', component: CreateOrthophonisteComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ADMINISTRATEUR'] } },
  { path: 'create-series', component: CreateSerieComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'create-animation', component: CreateAnimationComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'create-patient', component: CreatePatientComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'edit-animation', component: EditAnimationComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'edit-serie', component: EditSerieComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ORTHOPHONISTE', 'ADMINISTRATEUR'] } },
  { path: 'settings', component: ParametresComponent, canActivate: [AuthGuard] },

  // Patient routes
  { path: 'list-series-patient', component: ListSeriesPatientComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['PATIENT', 'ADMINISTRATEUR'] } },
  { path: 'list-animations-patient', component: ListAnimationsPatientComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['PATIENT', 'ADMINISTRATEUR'] } },
  { path: 'abonnements', component: AbonnementComponent, canActivate: [AuthGuard]},

  // Fallback route
  { path: '**', redirectTo: 'login' }
];
