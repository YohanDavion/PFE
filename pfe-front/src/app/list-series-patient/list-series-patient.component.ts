import {Component} from '@angular/core';
import {Serie} from '../interfaces/serie';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';
import {CommonModule} from "@angular/common";
import {ButtonModule} from 'primeng/button';
import {AnimationService} from '../services/animation.service';
import {SerieService} from '../services/serie.service';
import {Animation} from "../interfaces/animation";

@Component({
  selector: 'app-list-series-patient',
  imports: [CommonModule, ButtonModule],
  providers: [MessageService, SerieService, AnimationService],
  templateUrl: './list-series-patient.component.html',
  standalone: true,
  styleUrl: './list-series-patient.component.scss'
})
export class ListSeriesPatientComponent {
  series: Serie[] = [];
  animations: Animation[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private serieService: SerieService,
    private animationService: AnimationService,)
  {
  }

  ngOnInit(): void {
    this.serieService.getAllSeries().subscribe((series) => {
      this.series = series;
    })
  }

  startSerie(serieId: number) {
    this.goToPage('/list-animations-patient', {queryParams : {serieId}});
  }

  goToPage(pageName:string, data:any){
    this.router.navigate([`${pageName}`], data);
  }
}
