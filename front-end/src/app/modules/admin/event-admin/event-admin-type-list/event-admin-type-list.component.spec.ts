import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminTypeListComponent } from './event-admin-type-list.component';

describe('EventAdminTypeListComponent', () => {
  let component: EventAdminTypeListComponent;
  let fixture: ComponentFixture<EventAdminTypeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminTypeListComponent]
    });
    fixture = TestBed.createComponent(EventAdminTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
