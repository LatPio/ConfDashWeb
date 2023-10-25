import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserBookingListComponent} from "./user-booking-list/user-booking-list.component";
import {UserBookingCalendarComponent} from "./user-booking-calendar/user-booking-calendar.component";
import {UserBookingComponent} from "./user-booking.component";

const routes: Routes = [
  {path:'',
    component: UserBookingComponent,
  children: [
    {path: 'list', component:UserBookingListComponent},
    {path: 'calendar', component: UserBookingCalendarComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserBookingRoutingModule { }