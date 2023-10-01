import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminBookingEditComponent } from './localization-admin-booking-edit.component';

describe('LocalizationAdminBookingEditComponent', () => {
  let component: LocalizationAdminBookingEditComponent;
  let fixture: ComponentFixture<LocalizationAdminBookingEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminBookingEditComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminBookingEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
