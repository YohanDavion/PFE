import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EditAnimationComponent} from './edit-animation.component';

describe('EditAnimationComponent', () => {
  let component: EditAnimationComponent;
  let fixture: ComponentFixture<EditAnimationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAnimationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAnimationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
