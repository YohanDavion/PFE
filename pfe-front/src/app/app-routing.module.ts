import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParametresComponent } from './parametres/parametres.component';

const routes: Routes = [
  { path: 'parametres', component: ParametresComponent },
  { path: '', redirectTo: 'parametres', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 