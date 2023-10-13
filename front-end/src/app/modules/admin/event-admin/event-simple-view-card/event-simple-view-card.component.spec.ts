import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventSimpleViewCardComponent } from './event-simple-view-card.component';

describe('EventSimpleViewCardComponent', () => {
  let component: EventSimpleViewCardComponent;
  let fixture: ComponentFixture<EventSimpleViewCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventSimpleViewCardComponent]
    });
    fixture = TestBed.createComponent(EventSimpleViewCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
