import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLocalziationViewComponent } from './user-localziation-view.component';

describe('UserLocalziationViewComponent', () => {
  let component: UserLocalziationViewComponent;
  let fixture: ComponentFixture<UserLocalziationViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserLocalziationViewComponent]
    });
    fixture = TestBed.createComponent(UserLocalziationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
