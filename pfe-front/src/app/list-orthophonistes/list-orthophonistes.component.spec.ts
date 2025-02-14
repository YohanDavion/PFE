import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOrthophonistesComponent } from './list-orthophonistes.component';

describe('ListOrthophonistesComponent', () => {
  let component: ListOrthophonistesComponent;
  let fixture: ComponentFixture<ListOrthophonistesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListOrthophonistesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOrthophonistesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
