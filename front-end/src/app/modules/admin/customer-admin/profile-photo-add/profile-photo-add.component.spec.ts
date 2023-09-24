import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePhotoAddComponent } from './profile-photo-add.component';

describe('ProfilePhotoAddComponent', () => {
  let component: ProfilePhotoAddComponent;
  let fixture: ComponentFixture<ProfilePhotoAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfilePhotoAddComponent]
    });
    fixture = TestBed.createComponent(ProfilePhotoAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
