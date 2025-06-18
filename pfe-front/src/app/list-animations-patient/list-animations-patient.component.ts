import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from 'primeng/button';
import {MessageService} from 'primeng/api';
import {SerieService} from '../services/serie.service';
import {AnimationService} from '../services/animation.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Animation} from '../interfaces/animation';
import {Card} from 'primeng/card';

@Component({
  selector: 'app-list-animations-patient',
  imports: [CommonModule, ButtonModule, Card],
  providers: [MessageService, SerieService, AnimationService],
  templateUrl: './list-animations-patient.component.html',
  standalone: true,
  styleUrl: './list-animations-patient.component.scss'
})
export class ListAnimationsPatientComponent {
  currentIndex = 0;
  currentMode = 0;
  serieId : number | null = null;
  animations: Animation[] = [];

  constructor(
    private router: Router,
    public activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private serieService: SerieService,
    private animationService: AnimationService) {
  }

  async ngOnInit(): Promise<void> {
    this.activatedRoute.queryParams.subscribe(params => {
      this.serieId = params['serieId'];
      if (this.serieId != null) {
        this.animationService.getAnimationBySerie(this.serieId).subscribe(animations => {
          this.animations = animations;
        })
      }
    })
  }
  /**
   * Permet de jongler entre le GIF, le dessin et le son.
   * @param value
   */
  changeMode(value : number) {
    this.currentMode = value;
  }


  playSound() {
    let audio = new Audio();
    audio.src = 'data:' + this.animations[this.currentIndex].son.mimetype + ';base64, ' + this.animations[this.currentIndex].son.data;
    audio.load();
    audio.play();
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  nextAnimation() {
    if (this.currentIndex < this.animations.length-1) {
      this.currentIndex++;
    } else {
      // Fin de la série, on peut la valider
      if (this.serieId != null) {
        this.serieService.validateSerie(this.serieId).subscribe(() => {
          this.goToPage('list-series-patient')
        });
      }
    }
  }

}
