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
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {SharedModule} from "../../shared/shared.module";
import { LocalizationAdminMapListComponent } from './localization-admin-map-list/localization-admin-map-list.component';
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import { MapImageViewComponent } from './map-image-view/map-image-view.component';
import { MapImageUpdateComponent } from './map-image-update/map-image-update.component';
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from '@angular-material-components/datetime-picker';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {NgxMatMomentModule} from "@angular-material-components/moment-adapter";
import { LocalizationAdminListLightComponent } from './localization-admin-list-light/localization-admin-list-light.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { LocalizationCardComponent } from './localization-card/localization-card.component';
import { BookingCardComponent } from './booking-card/booking-card.component';


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
    MapImageAddComponent,
    LocalizationAdminMapListComponent,
    MapImageViewComponent,
    MapImageUpdateComponent,
    LocalizationAdminListLightComponent,
    LocalizationCardComponent,
    BookingCardComponent
  ],
  exports: [
    LocalizationAdminListLightComponent,
    LocalizationCardComponent,
    BookingCardComponent
  ],
  imports: [
    CommonModule,
    LocalizationAdminRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    SharedModule,
    MatCardModule,
    MatListModule,
    NgxMatDatetimePickerModule,
    MatDatepickerModule,
    NgxMatNativeDateModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    MatNativeDateModule,
    NgxMatMomentModule,
    MatCheckboxModule
  ]
})
export class LocalizationAdminModule { }
