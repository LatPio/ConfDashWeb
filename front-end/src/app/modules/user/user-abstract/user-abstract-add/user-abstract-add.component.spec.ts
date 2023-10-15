import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractAddComponent } from './user-abstract-add.component';

describe('UserAbstractAddComponent', () => {
  let component: UserAbstractAddComponent;
  let fixture: ComponentFixture<UserAbstractAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractAddComponent]
    });
    fixture = TestBed.createComponent(UserAbstractAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
