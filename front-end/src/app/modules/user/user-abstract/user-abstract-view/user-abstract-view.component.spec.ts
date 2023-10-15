import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractViewComponent } from './user-abstract-view.component';

describe('UserAbstractViewComponent', () => {
  let component: UserAbstractViewComponent;
  let fixture: ComponentFixture<UserAbstractViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractViewComponent]
    });
    fixture = TestBed.createComponent(UserAbstractViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
