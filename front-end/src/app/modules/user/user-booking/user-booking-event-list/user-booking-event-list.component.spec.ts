import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBookingEventListComponent } from './user-booking-event-list.component';

describe('UserBookingEventListComponent', () => {
  let component: UserBookingEventListComponent;
  let fixture: ComponentFixture<UserBookingEventListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserBookingEventListComponent]
    });
    fixture = TestBed.createComponent(UserBookingEventListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
