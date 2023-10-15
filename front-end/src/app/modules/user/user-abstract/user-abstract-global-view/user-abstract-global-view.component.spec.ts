import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractGlobalViewComponent } from './user-abstract-global-view.component';

describe('UserAbstractGlobalViewComponent', () => {
  let component: UserAbstractGlobalViewComponent;
  let fixture: ComponentFixture<UserAbstractGlobalViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractGlobalViewComponent]
    });
    fixture = TestBed.createComponent(UserAbstractGlobalViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
