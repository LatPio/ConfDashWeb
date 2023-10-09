import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminCardComponent } from './event-admin-card.component';

describe('EventAdminCardComponent', () => {
  let component: EventAdminCardComponent;
  let fixture: ComponentFixture<EventAdminCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminCardComponent]
    });
    fixture = TestBed.createComponent(EventAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
