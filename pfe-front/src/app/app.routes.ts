import {Routes} from '@angular/router';
import {ListSeriesComponent} from './list-series/list-series.component';
import {ListPatientsComponent} from './list-patients/list-patients.component';
import {ListOrthophonistesComponent} from './list-orthophonistes/list-orthophonistes.component';
import {LoginComponent} from './login/login.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {ListSeriesPatientComponent} from './list-series-patient/list-series-patient.component';
import {ListAnimationsPatientComponent} from './list-animations-patient/list-animations-patient.component';
import {CreateAnimationComponent} from './create-animation/create-animation.component';

export const routes: Routes = [
  { path: 'list-series', component: ListSeriesComponent},
  { path: 'list-patients', component: ListPatientsComponent},
  { path: 'list-orthophonistes', component: ListOrthophonistesComponent},
  { path: 'create-user', component: CreateUserComponent},
  { path: 'create-animation', component: CreateAnimationComponent},
  { path: 'login', component: LoginComponent},
  // Patient
  { path: 'list-series-patient', component: ListSeriesPatientComponent},
  { path: 'list-animations-patient', component: ListAnimationsPatientComponent},
];
