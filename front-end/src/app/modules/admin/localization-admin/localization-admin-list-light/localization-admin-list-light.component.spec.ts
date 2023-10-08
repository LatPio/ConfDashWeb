import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminListLightComponent } from './localization-admin-list-light.component';

describe('LocalizationAdminListLightComponent', () => {
  let component: LocalizationAdminListLightComponent;
  let fixture: ComponentFixture<LocalizationAdminListLightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminListLightComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminListLightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
