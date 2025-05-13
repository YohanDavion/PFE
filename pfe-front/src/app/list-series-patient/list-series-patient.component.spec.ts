import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ListSeriesPatientComponent} from './list-series-patient.component';

describe('ListSeriesPatientComponent', () => {
  let component: ListSeriesPatientComponent;
  let fixture: ComponentFixture<ListSeriesPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListSeriesPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListSeriesPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
