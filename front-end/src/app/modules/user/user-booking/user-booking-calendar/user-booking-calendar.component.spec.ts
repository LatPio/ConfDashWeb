import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBookingCalendarComponent } from './user-booking-calendar.component';

describe('UserBookingCalendarComponent', () => {
  let component: UserBookingCalendarComponent;
  let fixture: ComponentFixture<UserBookingCalendarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserBookingCalendarComponent]
    });
    fixture = TestBed.createComponent(UserBookingCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
