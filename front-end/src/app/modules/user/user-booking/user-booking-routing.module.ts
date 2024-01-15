import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserBookingListComponent} from "./user-booking-list/user-booking-list.component";
import {UserBookingComponent} from "./user-booking.component";
import {UserBookingEventListComponent} from "./user-booking-event-list/user-booking-event-list.component";

const routes: Routes = [
  {path:'',
    component: UserBookingComponent,
  children: [
    {path: 'list', component:UserBookingListComponent},
    {path: 'user-list', component:UserBookingEventListComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserBookingRoutingModule { }
