import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractGlobalListComponent } from './user-abstract-global-list.component';

describe('UserAbstractGlobalListComponent', () => {
  let component: UserAbstractGlobalListComponent;
  let fixture: ComponentFixture<UserAbstractGlobalListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractGlobalListComponent]
    });
    fixture = TestBed.createComponent(UserAbstractGlobalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
