import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationCardComponent } from './localization-card.component';

describe('LocalizationCardComponent', () => {
  let component: LocalizationCardComponent;
  let fixture: ComponentFixture<LocalizationCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationCardComponent]
    });
    fixture = TestBed.createComponent(LocalizationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
