import {Component} from '@angular/core';
import {Button, ButtonDirective} from "primeng/button";
import {ConfirmationService, MessageService, PrimeTemplate} from "primeng/api";
import {TableModule} from "primeng/table";
import {Toast} from "primeng/toast";
import {Animation} from '../interfaces/animation';
import {Router} from '@angular/router';
import {AnimationService} from '../services/animation.service';
import {Badge} from 'primeng/badge';
import {ConfirmDialog} from 'primeng/confirmdialog';
import {Dialog} from 'primeng/dialog';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-list-animations',
  imports: [
    CommonModule,
    ButtonDirective,
    PrimeTemplate,
    TableModule,
    Toast,
    Badge,
    ConfirmDialog,
    Dialog,
    Button
  ],
  providers: [MessageService, ConfirmationService, AnimationService],
  templateUrl: './list-animations.component.html',
  styleUrl: './list-animations.component.scss'
})
export class ListAnimationsComponent {
  animations: Animation[] = [];
  selectedAnimation: Animation | null = null;
  displayDialog: boolean = false;

  constructor(
    private messageService: MessageService,
    private animationService: AnimationService,
    private confirmationService: ConfirmationService,
    private router: Router) {
  }

  ngOnInit() {
    this.animationService.getAllAnimations().subscribe(animations => {
      this.animations = animations;
    })
  }

  getImageUrl(media: any): string {
    return `data:${media?.type};base64,${media?.data}`;
  }

  getAudioUrl(media: any): string {
    return `data:${media?.type};base64,${media?.data}`;
  }

  showAnimationDetails(animation: Animation) {
    this.selectedAnimation = animation;
    this.displayDialog = true;
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  goToEdit(id: number) {
    this.router.navigate(['/edit-animation'], {queryParams : {id}});
  }

  deleteOrDeactivate(animation: Animation) {
    if (animation.active) {
      animation.active = false;
      this.animationService.updateAnimation(animation).subscribe(() => {
        this.messageService.add({severity: 'warn', summary: 'Désactivée', detail: 'Animation désactivée'});
      });
    } else {
      console.log(animation)
      this.confirmationService.confirm({
        message: 'Voulez-vous vraiment supprimer cette animation ?',
        acceptLabel: 'Oui',
        rejectLabel: 'Non',
        accept: () => {
          this.animationService.deleteAnimation(animation.id).subscribe(() => {
            this.animations = this.animations.filter(a => a.id !== animation.id);
            this.messageService.add({severity: 'success', summary: 'Supprimée', detail: 'Animation supprimée'});
          });
        }
      });
    }
  }
}
