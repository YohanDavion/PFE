import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAnimationsPatientComponent } from './list-animations-patient.component';

describe('ListAnimationsPatientComponent', () => {
  let component: ListAnimationsPatientComponent;
  let fixture: ComponentFixture<ListAnimationsPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAnimationsPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAnimationsPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
