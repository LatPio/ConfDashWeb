import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAdminListLightComponent } from './event-admin-list-light.component';

describe('EventAdminListLightComponent', () => {
  let component: EventAdminListLightComponent;
  let fixture: ComponentFixture<EventAdminListLightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventAdminListLightComponent]
    });
    fixture = TestBed.createComponent(EventAdminListLightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
