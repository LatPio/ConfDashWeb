import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCalendarViewComponent } from './user-calendar-view.component';

describe('UserCalendarViewComponent', () => {
  let component: UserCalendarViewComponent;
  let fixture: ComponentFixture<UserCalendarViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCalendarViewComponent]
    });
    fixture = TestBed.createComponent(UserCalendarViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
