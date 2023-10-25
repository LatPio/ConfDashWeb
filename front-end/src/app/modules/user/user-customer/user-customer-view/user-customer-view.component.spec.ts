import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerViewComponent } from './user-customer-view.component';

describe('UserCustomerViewComponent', () => {
  let component: UserCustomerViewComponent;
  let fixture: ComponentFixture<UserCustomerViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerViewComponent]
    });
    fixture = TestBed.createComponent(UserCustomerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
