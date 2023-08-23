import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminComponent } from './localization-admin.component';

describe('LocalizationAdminComponent', () => {
  let component: LocalizationAdminComponent;
  let fixture: ComponentFixture<LocalizationAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
