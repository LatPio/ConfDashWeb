import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminEditComponent } from './localization-admin-edit.component';

describe('LocalizationAdminEditComponent', () => {
  let component: LocalizationAdminEditComponent;
  let fixture: ComponentFixture<LocalizationAdminEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminEditComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
