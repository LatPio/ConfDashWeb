import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLocalizationListComponent } from './user-localization-list.component';

describe('UserLocalizationListComponent', () => {
  let component: UserLocalizationListComponent;
  let fixture: ComponentFixture<UserLocalizationListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserLocalizationListComponent]
    });
    fixture = TestBed.createComponent(UserLocalizationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
