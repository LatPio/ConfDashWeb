import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractAttachFileButtonComponent } from './user-abstract-attach-file-button.component';

describe('UserAbstractAttachFileButtonComponent', () => {
  let component: UserAbstractAttachFileButtonComponent;
  let fixture: ComponentFixture<UserAbstractAttachFileButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractAttachFileButtonComponent]
    });
    fixture = TestBed.createComponent(UserAbstractAttachFileButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
