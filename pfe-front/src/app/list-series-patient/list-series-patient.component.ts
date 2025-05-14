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
    this.initializeTestData();
  }

  initializeTestData() {
    const animation1 = new Animation(1, "Animation 1 contenu", true);
    const animation2 = new Animation(2, "Animation 2 contenu", false);

    const serie1 = new Serie(1, "Série 1", [animation1, animation2]);
    const serie2 = new Serie(2, "Série 2", [animation1, animation2]);

    this.series = [serie1, serie2];
  }

  startSerie(serieId: number) {
    this.animationService.getAnimationBySerie(serieId).subscribe(animations => {
      console.log({queryParams : {animations}})
      this.goToPage('/list-animations-patient', {queryParams : {animations}});
    });
  }

  goToPage(pageName:string, data:any){
    this.router.navigate([`${pageName}`], data);
  }
}
