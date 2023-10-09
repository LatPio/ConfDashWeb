import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminUpdateComponent } from './event-admin-update.component';

describe('EventAdminUpdateComponent', () => {
  let component: EventAdminUpdateComponent;
  let fixture: ComponentFixture<EventAdminUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminUpdateComponent]
    });
    fixture = TestBed.createComponent(EventAdminUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
