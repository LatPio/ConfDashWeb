import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminAcceptedComponent } from './abstract-admin-accepted.component';

describe('AbstractsAdminAcceptedComponent', () => {
  let component: AbstractAdminAcceptedComponent;
  let fixture: ComponentFixture<AbstractAdminAcceptedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminAcceptedComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminAcceptedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
