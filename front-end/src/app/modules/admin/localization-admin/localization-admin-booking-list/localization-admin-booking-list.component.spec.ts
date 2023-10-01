import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminBookingListComponent } from './localization-admin-booking-list.component';

describe('LocalizationAdminBookingListComponent', () => {
  let component: LocalizationAdminBookingListComponent;
  let fixture: ComponentFixture<LocalizationAdminBookingListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminBookingListComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminBookingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
