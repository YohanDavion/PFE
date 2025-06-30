import {Component} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {DataViewModule} from 'primeng/dataview';
import {Serie} from '../interfaces/serie';
import {TagModule} from 'primeng/tag';
import {CommonModule} from '@angular/common';
import {ToastModule} from 'primeng/toast';
import {Router} from '@angular/router';
import {ConfirmationService, MessageService} from 'primeng/api';
import {TableModule} from 'primeng/table';
import {SerieService} from '../services/serie.service';
import {ConfirmDialog} from 'primeng/confirmdialog';
import {Badge} from 'primeng/badge';


@Component({
  selector: 'app-list-series',
  imports: [DataViewModule, ButtonModule, TagModule, CommonModule, ToastModule, TableModule, ConfirmDialog, Badge],
  providers: [MessageService, ConfirmationService, SerieService],
  templateUrl: './list-series.component.html',
  styleUrl: './list-series.component.scss'
})
export class ListSeriesComponent {
  series: Serie[] = [];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private confirmationService : ConfirmationService,
    private serieService : SerieService) {
  }

  ngOnInit(): void {
    this.serieService.getAllSeries().subscribe(series => this.series = series)
  }

  goToEdit(id: number) {
    this.router.navigate(['/edit-serie'], { queryParams: { id } });
  }

  deleteOrDeactivate(serie: Serie) {
    if (serie.active) {
      serie.active = false;
      this.serieService.updateSerie(serie.id, serie).subscribe(() => {
        this.messageService.add({severity: 'warn', summary: 'Désactivée', detail: 'Série désactivée'});
      });
    } else {
      this.confirmationService.confirm({
        message: 'Voulez-vous vraiment supprimer cette série ?',
        acceptLabel: 'Oui',
        rejectLabel: 'Non',
        accept: () => {
          this.serieService.deleteSerie(serie.id).subscribe(() => {
            this.series = this.series.filter(s => s.id !== serie.id);
            this.messageService.add({severity: 'success', summary: 'Supprimée', detail: 'Série supprimée'});
          });
        }
      });
    }
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }
}
