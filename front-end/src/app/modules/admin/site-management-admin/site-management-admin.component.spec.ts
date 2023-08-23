import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteManagementAdminComponent } from './site-management-admin.component';

describe('SiteManagementAdminComponent', () => {
  let component: SiteManagementAdminComponent;
  let fixture: ComponentFixture<SiteManagementAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SiteManagementAdminComponent]
    });
    fixture = TestBed.createComponent(SiteManagementAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
