import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLocalizationComponent } from './user-localization.component';

describe('UserLocalizationComponent', () => {
  let component: UserLocalizationComponent;
  let fixture: ComponentFixture<UserLocalizationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserLocalizationComponent]
    });
    fixture = TestBed.createComponent(UserLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
