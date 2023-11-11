import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserBookingRoutingModule } from './user-booking-routing.module';
import { UserBookingComponent } from './user-booking.component';
import { UserBookingCalendarComponent } from './user-booking-calendar/user-booking-calendar.component';
import { UserBookingListComponent } from './user-booking-list/user-booking-list.component';
import {AbstractsAdminModule} from "../../admin/abstracts-admin/abstracts-admin.module";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../../shared/shared.module";
import {EventAdminModule} from "../../admin/event-admin/event-admin.module";
import { UserBookingEventListComponent } from './user-booking-event-list/user-booking-event-list.component';


@NgModule({
  declarations: [
    UserBookingComponent,
    UserBookingCalendarComponent,
    UserBookingListComponent,
    UserBookingEventListComponent
  ],
  imports: [
    CommonModule,
    UserBookingRoutingModule,
    AbstractsAdminModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    SharedModule,
    EventAdminModule
  ]
})
export class UserBookingModule { }
