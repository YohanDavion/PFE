import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EditSerieComponent} from './edit-serie.component';

describe('EditSerieComponent', () => {
  let component: EditSerieComponent;
  let fixture: ComponentFixture<EditSerieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditSerieComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditSerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
