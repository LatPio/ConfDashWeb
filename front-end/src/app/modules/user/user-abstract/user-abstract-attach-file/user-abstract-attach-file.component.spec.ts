import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractAttachFileComponent } from './user-abstract-attach-file.component';

describe('UserAbstractAttachFileComponent', () => {
  let component: UserAbstractAttachFileComponent;
  let fixture: ComponentFixture<UserAbstractAttachFileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractAttachFileComponent]
    });
    fixture = TestBed.createComponent(UserAbstractAttachFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
