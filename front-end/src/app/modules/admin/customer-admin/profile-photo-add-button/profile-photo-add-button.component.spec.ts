import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePhotoAddButtonComponent } from './profile-photo-add-button.component';

describe('ProfilePhotoAddButtonComponent', () => {
  let component: ProfilePhotoAddButtonComponent;
  let fixture: ComponentFixture<ProfilePhotoAddButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfilePhotoAddButtonComponent]
    });
    fixture = TestBed.createComponent(ProfilePhotoAddButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
