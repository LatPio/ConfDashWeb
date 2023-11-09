import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCalendarRoomComponent } from './user-calendar-room.component';

describe('UserCalendarRoomComponent', () => {
  let component: UserCalendarRoomComponent;
  let fixture: ComponentFixture<UserCalendarRoomComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCalendarRoomComponent]
    });
    fixture = TestBed.createComponent(UserCalendarRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
