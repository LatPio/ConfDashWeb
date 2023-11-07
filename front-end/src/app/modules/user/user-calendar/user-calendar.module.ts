import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCalendarRoutingModule } from './user-calendar-routing.module';
import { UserCalendarComponent } from './user-calendar.component';


@NgModule({
  declarations: [
    UserCalendarComponent
  ],
  imports: [
    CommonModule,
    UserCalendarRoutingModule
  ]
})
export class UserCalendarModule { }
