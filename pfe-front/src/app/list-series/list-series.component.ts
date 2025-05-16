import { Component,NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import { Serie } from '../interfaces/serie';
import {Animation} from '../interfaces/animation';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';


@Component({
  selector: 'app-list-series',
  imports: [DataViewModule,ButtonModule,TagModule,CommonModule,ToastModule,TableModule],
  providers: [MessageService],
  templateUrl: './list-series.component.html',
  styleUrl: './list-series.component.scss'
})
export class ListSeriesComponent {
  series: Serie[] = [];
  animations: Animation[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService
  )
    {
  }

  ngOnInit(): void {
    this.initializeTestData();
  }

  initializeTestData() {
    // const animation1 = new Animation(1, "Animation 1 contenu", true);
    // const animation2 = new Animation(2, "Animation 2 contenu", false);
    //
    // this.animations = [animation1, animation2];
    //
    // const serie1 = new Serie(1, "Série 1", [animation1, animation2]);
    // const serie2 = new Serie(2, "Série 2", [animation1, animation2]);
    //
    // this.series = [serie1, serie2];
  }

  test(){

  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Succés', detail: 'Suppréssion du Client' });
  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Echec', detail: 'Echec de la suppréssion du Client' });
}
}
