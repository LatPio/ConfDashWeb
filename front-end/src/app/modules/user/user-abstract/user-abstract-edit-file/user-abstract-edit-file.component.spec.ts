import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractEditFileComponent } from './user-abstract-edit-file.component';

describe('UserAbstractEditFileComponent', () => {
  let component: UserAbstractEditFileComponent;
  let fixture: ComponentFixture<UserAbstractEditFileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractEditFileComponent]
    });
    fixture = TestBed.createComponent(UserAbstractEditFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
