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
import { MapImageSimpleListComponent } from './map-image-simple-list/map-image-simple-list.component';
import { MapImageAddComponent } from './map-image-add/map-image-add.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    LocalizationAdminStatsComponent,
    LocalizationAdminListComponent,
    LocalizationAdminAddComponent,
    LocalizationAdminBookingAddComponent,
    LocalizationAdminBookingEditComponent,
    LocalizationAdminBookingListComponent,
    LocalizationAdminEditComponent,
    LocalizationAdminViewComponent,
    MapImageSimpleListComponent,
    MapImageAddComponent
  ],
  imports: [
    CommonModule,
    LocalizationAdminRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    ReactiveFormsModule
  ]
})
export class LocalizationAdminModule { }
