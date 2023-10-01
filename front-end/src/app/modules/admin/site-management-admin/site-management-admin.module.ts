import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SiteManagementAdminRoutingModule } from './site-management-admin-routing.module';
import { SiteManagementAdminStatsComponent } from './site-management-admin-stats/site-management-admin-stats.component';


@NgModule({
  declarations: [
    SiteManagementAdminStatsComponent
  ],
  imports: [
    CommonModule,
    SiteManagementAdminRoutingModule
  ]
})
export class SiteManagementAdminModule { }
