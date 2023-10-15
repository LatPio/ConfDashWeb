import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerListComponent } from './user-customer-list.component';

describe('UserCustomerListComponent', () => {
  let component: UserCustomerListComponent;
  let fixture: ComponentFixture<UserCustomerListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerListComponent]
    });
    fixture = TestBed.createComponent(UserCustomerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
