import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SuccessAbonnementComponent} from './success-abonnement.component';

describe('SuccessAbonnementComponent', () => {
  let component: SuccessAbonnementComponent;
  let fixture: ComponentFixture<SuccessAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuccessAbonnementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
