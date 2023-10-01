import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminViewComponent } from './localization-admin-view.component';

describe('LocalizationAdminViewComponent', () => {
  let component: LocalizationAdminViewComponent;
  let fixture: ComponentFixture<LocalizationAdminViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminViewComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
