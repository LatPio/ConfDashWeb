import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminListComponent } from './event-admin-list.component';

describe('EventAdminListComponent', () => {
  let component: EventAdminListComponent;
  let fixture: ComponentFixture<EventAdminListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminListComponent]
    });
    fixture = TestBed.createComponent(EventAdminListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
