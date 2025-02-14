import { Routes } from '@angular/router';
import { ListSeriesComponent } from './list-series/list-series.component';
import { ListPatientsComponent } from './list-patients/list-patients.component';
import { ListOrthophonistesComponent } from './list-orthophonistes/list-orthophonistes.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
  { path: 'list-series', component: ListSeriesComponent},
  { path: 'list-patients', component: ListPatientsComponent},
  { path: 'list-orthophonistes', component: ListOrthophonistesComponent},
  { path: 'login', component: LoginComponent},
];
