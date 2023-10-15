import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerProfileComponent } from './user-customer-profile.component';

describe('UserCustomerProfileComponent', () => {
  let component: UserCustomerProfileComponent;
  let fixture: ComponentFixture<UserCustomerProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerProfileComponent]
    });
    fixture = TestBed.createComponent(UserCustomerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
