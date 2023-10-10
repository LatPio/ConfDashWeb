import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminTypeListLightComponent } from './event-admin-type-list-light.component';

describe('EventAdminTypeListLightComponent', () => {
  let component: EventAdminTypeListLightComponent;
  let fixture: ComponentFixture<EventAdminTypeListLightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminTypeListLightComponent]
    });
    fixture = TestBed.createComponent(EventAdminTypeListLightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
