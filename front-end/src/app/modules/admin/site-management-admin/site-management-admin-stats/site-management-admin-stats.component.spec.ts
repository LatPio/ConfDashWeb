import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteManagementAdminStatsComponent } from './site-management-admin-stats.component';

describe('SiteManagementAdminStatsComponent', () => {
  let component: SiteManagementAdminStatsComponent;
  let fixture: ComponentFixture<SiteManagementAdminStatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SiteManagementAdminStatsComponent]
    });
    fixture = TestBed.createComponent(SiteManagementAdminStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
