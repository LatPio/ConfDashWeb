import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractEditComponent } from './user-abstract-edit.component';

describe('UserAbstractEditComponent', () => {
  let component: UserAbstractEditComponent;
  let fixture: ComponentFixture<UserAbstractEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractEditComponent]
    });
    fixture = TestBed.createComponent(UserAbstractEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
