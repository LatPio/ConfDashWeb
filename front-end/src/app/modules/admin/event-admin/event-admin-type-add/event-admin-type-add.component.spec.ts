import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminTypeAddComponent } from './event-admin-type-add.component';

describe('EventAdminTypeAddComponent', () => {
  let component: EventAdminTypeAddComponent;
  let fixture: ComponentFixture<EventAdminTypeAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminTypeAddComponent]
    });
    fixture = TestBed.createComponent(EventAdminTypeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
