import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffiliationCorrectionComponent } from './affiliation-correction.component';

describe('AffiliationCorrectionComponent', () => {
  let component: AffiliationCorrectionComponent;
  let fixture: ComponentFixture<AffiliationCorrectionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AffiliationCorrectionComponent]
    });
    fixture = TestBed.createComponent(AffiliationCorrectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
