import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AnimationService} from '../services/animation.service';
import {CommonModule} from '@angular/common';
import {InputTextModule} from 'primeng/inputtext';
import {CardModule} from 'primeng/card';
import {ButtonModule} from 'primeng/button';
import {Animation} from '../interfaces/animation';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-edit-animation',
  standalone: true,
  imports: [
    CommonModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    CardModule,
    ButtonModule
  ],
  providers : [MessageService, AnimationService],
  templateUrl: './edit-animation.component.html',
  styleUrl: './edit-animation.component.scss'
})
export class EditAnimationComponent implements OnInit {
  form: FormGroup;
  animationId!: number;
  animationData!: Animation;
  files: { [key: string]: File } = {};

  constructor(
    public activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private animationService: AnimationService,
    private router: Router
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.animationId = params['id'];
      this.animationService.getAnimation(this.animationId).subscribe(data => {
        this.animationData = data;
        this.form.patchValue({ libelle: data.libelle });
      });
    });
  }

  onFileSelected(event: any, field: string) {
    const file = event.target.files[0];
    if (file) {
      this.files[field] = file;
    }
  }

  getMediaUrl(media: any): string {
    return media ? `data:${media.type};base64,${media.data}` : '';
  }

  submitForm() {
    if (this.form.invalid) {
      console.log('Formulaire invalide');
      return;
    }

    const formData = new FormData();
    formData.append('libelle', this.form.get('libelle')?.value);
    if (this.files['gif']) {
      formData.append('gif', this.files['gif']);
    }
    if (this.files['dessin']) {
      formData.append('dessin', this.files['dessin']);
    }
    if (this.files['son']) {
      formData.append('son', this.files['son']);
    }

    this.animationService.updateAnimationFormData(this.animationId, formData)
      .subscribe(() => this.router.navigate(['/list-animations']));
  }
}
