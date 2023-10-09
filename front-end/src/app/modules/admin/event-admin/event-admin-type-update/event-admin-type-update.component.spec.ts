import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminTypeUpdateComponent } from './event-admin-type-update.component';

describe('EventAdminTypeUpdateComponent', () => {
  let component: EventAdminTypeUpdateComponent;
  let fixture: ComponentFixture<EventAdminTypeUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminTypeUpdateComponent]
    });
    fixture = TestBed.createComponent(EventAdminTypeUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
