import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerProfileEditComponent } from './user-customer-profile-edit.component';

describe('UserCustomerProfileEditComponent', () => {
  let component: UserCustomerProfileEditComponent;
  let fixture: ComponentFixture<UserCustomerProfileEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerProfileEditComponent]
    });
    fixture = TestBed.createComponent(UserCustomerProfileEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
