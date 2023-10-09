import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminAddComponent } from './event-admin-add.component';

describe('EventAdminAddComponent', () => {
  let component: EventAdminAddComponent;
  let fixture: ComponentFixture<EventAdminAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminAddComponent]
    });
    fixture = TestBed.createComponent(EventAdminAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
