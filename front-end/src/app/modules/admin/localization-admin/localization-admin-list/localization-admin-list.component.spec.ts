import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalizationAdminListComponent } from './localization-admin-list.component';

describe('LocalizationAdminListComponent', () => {
  let component: LocalizationAdminListComponent;
  let fixture: ComponentFixture<LocalizationAdminListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocalizationAdminListComponent]
    });
    fixture = TestBed.createComponent(LocalizationAdminListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
