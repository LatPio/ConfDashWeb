import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocalizationAdminRoutingModule } from './localization-admin-routing.module';
import { LocalizationAdminStatsComponent } from './localization-admin-stats/localization-admin-stats.component';
import { LocalizationAdminListComponent } from './localization-admin-list/localization-admin-list.component';
import { LocalizationAdminAddComponent } from './localization-admin-add/localization-admin-add.component';
import { LocalizationAdminBookingAddComponent } from './localization-admin-booking-add/localization-admin-booking-add.component';
import { LocalizationAdminBookingEditComponent } from './localization-admin-booking-edit/localization-admin-booking-edit.component';
import { LocalizationAdminBookingListComponent } from './localization-admin-booking-list/localization-admin-booking-list.component';
import { LocalizationAdminEditComponent } from './localization-admin-edit/localization-admin-edit.component';
import { LocalizationAdminViewComponent } from './localization-admin-view/localization-admin-view.component';


@NgModule({
  declarations: [
    LocalizationAdminStatsComponent,
    LocalizationAdminListComponent,
    LocalizationAdminAddComponent,
    LocalizationAdminBookingAddComponent,
    LocalizationAdminBookingEditComponent,
    LocalizationAdminBookingListComponent,
    LocalizationAdminEditComponent,
    LocalizationAdminViewComponent
  ],
  imports: [
    CommonModule,
    LocalizationAdminRoutingModule
  ]
})
export class LocalizationAdminModule { }
