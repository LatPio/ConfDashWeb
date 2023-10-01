import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminAddComponent } from './localization-admin-add.component';

describe('LocalizationAdminAddComponent', () => {
  let component: LocalizationAdminAddComponent;
  let fixture: ComponentFixture<LocalizationAdminAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminAddComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
