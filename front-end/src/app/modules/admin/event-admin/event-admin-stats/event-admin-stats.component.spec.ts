import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminStatsComponent } from './event-admin-stats.component';

describe('EventAdminStatsComponent', () => {
  let component: EventAdminStatsComponent;
  let fixture: ComponentFixture<EventAdminStatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminStatsComponent]
    });
    fixture = TestBed.createComponent(EventAdminStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
