import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserBookingRoutingModule } from './user-booking-routing.module';
import { UserBookingComponent } from './user-booking.component';
import { UserBookingCalendarComponent } from './user-booking-calendar/user-booking-calendar.component';
import { UserBookingListComponent } from './user-booking-list/user-booking-list.component';


@NgModule({
  declarations: [
    UserBookingComponent,
    UserBookingCalendarComponent,
    UserBookingListComponent
  ],
  imports: [
    CommonModule,
    UserBookingRoutingModule
  ]
})
export class UserBookingModule { }
