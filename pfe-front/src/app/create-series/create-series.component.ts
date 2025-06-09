import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {SerieService} from '../services/serie.service';
import {AnimationService} from '../services/animation.service';
import {Animation} from '../interfaces/animation';
import {InputTextModule} from 'primeng/inputtext';
import {CommonModule} from '@angular/common';
import {CardModule} from 'primeng/card';
import {MultiSelect} from 'primeng/multiselect';
import {Dialog} from 'primeng/dialog';
import {CreateAnimationComponent} from '../create-animation/create-animation.component';
import {ButtonDirective} from 'primeng/button'; // à adapter à ta structure

@Component({
  selector: 'app-create-serie',
  standalone: true,
  templateUrl: './create-series.component.html',
  styleUrls: ['./create-serie.component.scss'],
  imports: [
    InputTextModule,
    FormsModule,
    CommonModule,
    CardModule,
    ReactiveFormsModule,
    MultiSelect,
    Dialog,
    CreateAnimationComponent,
    ButtonDirective,
  ]
})
export class CreateSerieComponent {
  form: FormGroup;
  animations: Animation[] = [];
  showCreateAnimationDialog = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private serieService: SerieService,
    private animationService: AnimationService
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required],
      animations: [[], Validators.required]
    });

    this.loadAnimations();
  }

  loadAnimations() {
    this.animationService.getAllAnimations().subscribe(data => this.animations = data);
  }

  submitSerieForm() {
    if (this.form.invalid) {
      return;
    }

    const payload = {
      libelle: this.form.value.libelle,
      animationIds: this.form.value.animations.map((a: Animation) => a.id),
      active: true
    };

    this.serieService.createSerie(payload).subscribe({
      next: res => console.log('Série créée :', res),
      error: err => console.error('Erreur création série :', err)
    });
  }

  openCreateAnimationDialog() {
    this.showCreateAnimationDialog = true;
  }

  onAnimationCreated(newAnimation: Animation) {
    this.animations.push(newAnimation);
    const selected = this.form.get('animations')?.value || [];
    selected.push(newAnimation);
    this.form.get('animations')?.setValue(selected);
    this.showCreateAnimationDialog = false;
  }
}
