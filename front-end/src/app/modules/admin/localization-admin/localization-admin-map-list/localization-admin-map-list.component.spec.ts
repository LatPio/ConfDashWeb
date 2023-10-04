import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminMapListComponent } from './localization-admin-map-list.component';

describe('LocalizationAdminMapListComponent', () => {
  let component: LocalizationAdminMapListComponent;
  let fixture: ComponentFixture<LocalizationAdminMapListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminMapListComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminMapListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
