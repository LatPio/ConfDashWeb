import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminBookingAddComponent } from './localization-admin-booking-add.component';

describe('LocalizationAdminBookingAddComponent', () => {
  let component: LocalizationAdminBookingAddComponent;
  let fixture: ComponentFixture<LocalizationAdminBookingAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminBookingAddComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminBookingAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
