import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ValidateAbonnementComponent} from './validate-abonnement.component';

describe('ValidateAbonnementComponent', () => {
  let component: ValidateAbonnementComponent;
  let fixture: ComponentFixture<ValidateAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ValidateAbonnementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValidateAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
