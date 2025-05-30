import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAnimationComponent } from './create-animation.component';

describe('CreateAnimationComponent', () => {
  let component: CreateAnimationComponent;
  let fixture: ComponentFixture<CreateAnimationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateAnimationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateAnimationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
