import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerPhotoAddButtonComponent } from './user-customer-photo-add-button.component';

describe('UserCustomerPhotoAddButtonComponent', () => {
  let component: UserCustomerPhotoAddButtonComponent;
  let fixture: ComponentFixture<UserCustomerPhotoAddButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerPhotoAddButtonComponent]
    });
    fixture = TestBed.createComponent(UserCustomerPhotoAddButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
