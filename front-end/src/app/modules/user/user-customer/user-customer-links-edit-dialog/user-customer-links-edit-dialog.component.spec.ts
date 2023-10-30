import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCustomerLinksEditDialogComponent } from './user-customer-links-edit-dialog.component';

describe('UserCustomerLinksEditDialogComponent', () => {
  let component: UserCustomerLinksEditDialogComponent;
  let fixture: ComponentFixture<UserCustomerLinksEditDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserCustomerLinksEditDialogComponent]
    });
    fixture = TestBed.createComponent(UserCustomerLinksEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
