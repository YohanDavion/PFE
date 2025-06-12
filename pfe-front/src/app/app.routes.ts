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
import {CreateSerieComponent} from './create-series/create-series.component';
import {ListAnimationsComponent} from './list-animations/list-animations.component';
import {EditAnimationComponent} from './edit-animation/edit-animation.component';
import {EditSerieComponent} from './edit-serie/edit-serie.component';

export const routes: Routes = [
  { path: 'list-series', component: ListSeriesComponent},
  { path: 'list-animations', component: ListAnimationsComponent},
  { path: 'list-patients', component: ListPatientsComponent},
  { path: 'list-orthophonistes', component: ListOrthophonistesComponent},
  { path: 'create-user', component: CreateUserComponent},
  { path: 'create-series', component: CreateSerieComponent},
  { path: 'create-animation', component: CreateAnimationComponent},
  { path: 'create-patient', component: CreatePatientComponent},
  { path: 'edit-animation', component: EditAnimationComponent},
  { path: 'edit-serie', component: EditSerieComponent},
  { path: 'login', component: LoginComponent},
  { path: 'list-series-patient', component: ListSeriesPatientComponent},
  { path: 'list-animations-patient', component: ListAnimationsPatientComponent},
  { path: 'settings', component: ParametresComponent},
];
