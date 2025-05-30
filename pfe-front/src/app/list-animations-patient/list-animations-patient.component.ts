import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from 'primeng/button';
import {MessageService} from 'primeng/api';
import {SerieService} from '../services/serie.service';
import {AnimationService} from '../services/animation.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Animation} from '../interfaces/animation';
import {Observable} from 'rxjs';
import {Card} from 'primeng/card';
import {Media} from '../models/media.model';

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
  animations: Animation[] = [];

  state$: Observable<object> | undefined;

  constructor(
    private router: Router,
    public activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private serieService: SerieService,
    private animationService: AnimationService) {
  }

  async ngOnInit(): Promise<void> {
    this.activatedRoute.queryParams.subscribe(params => {
      let serieId = params['serieId'];
      if (serieId != null) {
        this.animationService.getAnimationBySerie(serieId).subscribe(animations => {
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

  nextAnimation() {
    if (this.currentIndex < this.animations.length-1) {
      this.currentIndex++;
    }
  }

  getCurrentMedia() : Media {
    let none = {data: '', id: 0, mimetype: '', dataBase64:''};
    if (this.currentMode === 0) {
      {
        // console.log(this.animations[this.currentIndex])
        // return this.animations[this.currentIndex] != null ? this.animations[this.currentIndex].gif : none;
      }
      {
        // return this.animations[this.currentIndex] != null ? this.animations[this.currentIndex].image : none;
      }
    } else {
      {
        // return this.animations[this.currentIndex] != null ? this.animations[this.currentIndex].image : none;
      }
    }
    return none;
  }
}
