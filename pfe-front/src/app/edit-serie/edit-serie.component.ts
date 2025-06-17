import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {InputTextModule} from 'primeng/inputtext';
import {CardModule} from 'primeng/card';
import {MultiSelect} from 'primeng/multiselect';
import {ButtonDirective} from 'primeng/button';
import {SerieService} from '../services/serie.service';
import {AnimationService} from '../services/animation.service';
import {Animation} from '../interfaces/animation';
import {Serie} from '../interfaces/serie';

@Component({
  selector: 'app-edit-serie',
  standalone: true,
  templateUrl: './edit-serie.component.html',
  styleUrls: ['./edit-serie.component.scss'],
  imports: [
    CommonModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    CardModule,
    MultiSelect,
    ButtonDirective
  ],
  providers: [AnimationService, SerieService],
})
export class EditSerieComponent implements OnInit {
  form: FormGroup;
  animations: Animation[] = [];
  serieId!: number;
  serie : Serie | null = null;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private serieService: SerieService,
    private animationService: AnimationService
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required],
      animations: [[], Validators.required]
    });
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.serieId = params['id'];

      // 1. Récupérer toutes les animations d'abord
      this.animationService.getAllAnimations().subscribe(animations => {
        this.animations = animations;

        // 2. Puis récupérer la série
        this.serieService.getSerie(this.serieId).subscribe(data => {
          this.serie = data;

          // 3. Mapper les animations de la série avec les instances réelles de this.animations
          const selectedAnimations = data.animations.map((serieAnim: any) =>
            this.animations.find(a => a.id === serieAnim.id)
          ).filter(a => a); // retirer les undefined au cas où

          this.form.patchValue({
            libelle: data.libelle,
            animations: selectedAnimations
          });
        });
      });
    });
  }


  // ngOnInit(): void {
  //   this.animationService.getAllAnimations().subscribe(animations => this.animations = animations);
  //   this.activatedRoute.queryParams.subscribe(params => {
  //     this.serieId = params['id'];
  //     this.serieService.getSerie(this.serieId).subscribe(data => {
  //       this.serie = data;
  //       this.form.patchValue({
  //         libelle: data.libelle,
  //         animations: data.animations
  //       });
  //     });
  //   });
  // }

  submitEditForm() {
    if (this.form.invalid) return;

    const payload = {
      id : this.serieId,
      libelle: this.form.value.libelle,
      animations: this.form.value.animations,
      active: true
    };

    this.serieService.updateSerie(this.serieId, payload).subscribe({
      next: () => this.router.navigate(['/list-series']),
      error: err => console.error('Erreur lors de la mise à jour :', err)
    });
  }
}
