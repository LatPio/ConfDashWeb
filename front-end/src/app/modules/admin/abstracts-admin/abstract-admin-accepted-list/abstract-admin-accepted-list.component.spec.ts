import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminAcceptedListComponent } from './abstract-admin-accepted-list.component';

describe('AbstractAdminAcceptedListComponent', () => {
  let component: AbstractAdminAcceptedListComponent;
  let fixture: ComponentFixture<AbstractAdminAcceptedListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminAcceptedListComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminAcceptedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
