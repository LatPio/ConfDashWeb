import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CutomerCardComponent } from './cutomer-card.component';

describe('CutomerCardComponent', () => {
  let component: CutomerCardComponent;
  let fixture: ComponentFixture<CutomerCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CutomerCardComponent]
    });
    fixture = TestBed.createComponent(CutomerCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
