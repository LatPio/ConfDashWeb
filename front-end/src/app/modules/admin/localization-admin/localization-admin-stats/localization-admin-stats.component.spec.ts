import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminStatsComponent } from './localization-admin-stats.component';

describe('LocalizationAdminStatsComponent', () => {
  let component: LocalizationAdminStatsComponent;
  let fixture: ComponentFixture<LocalizationAdminStatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminStatsComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
