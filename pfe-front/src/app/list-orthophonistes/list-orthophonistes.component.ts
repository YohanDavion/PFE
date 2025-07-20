import {Component} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {DataViewModule} from 'primeng/dataview';
import {Orthophoniste} from '../models/orthophoniste.model';
import {TagModule} from 'primeng/tag';
import {CommonModule} from '@angular/common';
import {ToastModule} from 'primeng/toast';
import {Router} from '@angular/router';
import {ConfirmationService, MessageService} from 'primeng/api';
import {TableModule} from 'primeng/table';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {OrthophonisteService} from '../services/orthophoniste.service';

@Component({
  selector: 'app-list-orthophonistes',
  imports: [DataViewModule,ButtonModule,TagModule,CommonModule,ToastModule,TableModule,ConfirmDialogModule],
  providers: [MessageService, ConfirmationService, OrthophonisteService],
  templateUrl: './list-orthophonistes.component.html',
  styleUrl: './list-orthophonistes.component.scss'
})
export class ListOrthophonistesComponent {
  orthophonistes: Orthophoniste[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private orthophonisteService: OrthophonisteService
  )
  {
  }

  ngOnInit(): void {
    this.orthophonisteService.getAllOrthophonistes().subscribe(orthophonistes => this.orthophonistes = orthophonistes);
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Suppression de l\'orthophoniste' });
  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Echec', detail: 'Echec de la suppression de l\'orthophoniste' });
  }

  editOrthophoniste(orthophoniste: Orthophoniste) {
    this.router.navigate(['/create-orthophoniste'], { queryParams: { orthophonisteId: orthophoniste.id } });
  }

  deleteOrthophoniste(orthophoniste: Orthophoniste) {
    this.confirmationService.confirm({
      message: 'Voulez-vous vraiment supprimer cet orthophoniste ?',
      acceptLabel: 'Oui',
      rejectLabel: 'Non',
      accept: () => {
        this.orthophonisteService.deleteOrthophoniste(orthophoniste.id).subscribe(() => {
          this.orthophonistes = this.orthophonistes.filter(o => o.id !== orthophoniste.id);
          this.messageService.add({severity: 'success', summary: 'Supprimé', detail: 'Orthophoniste supprimé'});
        });
      }
    });
  }
}
