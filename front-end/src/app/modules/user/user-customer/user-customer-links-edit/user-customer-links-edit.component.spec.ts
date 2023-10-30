import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerLinksEditComponent } from './user-customer-links-edit.component';

describe('UserCustomerLinksEditComponent', () => {
  let component: UserCustomerLinksEditComponent;
  let fixture: ComponentFixture<UserCustomerLinksEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerLinksEditComponent]
    });
    fixture = TestBed.createComponent(UserCustomerLinksEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
