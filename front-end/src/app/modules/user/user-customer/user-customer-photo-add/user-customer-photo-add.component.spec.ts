import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerPhotoAddComponent } from './user-customer-photo-add.component';

describe('UserCustomerPhotoAddComponent', () => {
  let component: UserCustomerPhotoAddComponent;
  let fixture: ComponentFixture<UserCustomerPhotoAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerPhotoAddComponent]
    });
    fixture = TestBed.createComponent(UserCustomerPhotoAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
