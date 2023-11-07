import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCalendarComponent } from './user-calendar.component';

describe('UserCalendarComponent', () => {
  let component: UserCalendarComponent;
  let fixture: ComponentFixture<UserCalendarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCalendarComponent]
    });
    fixture = TestBed.createComponent(UserCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
