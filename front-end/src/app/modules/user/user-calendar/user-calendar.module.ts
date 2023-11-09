import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCalendarRoutingModule } from './user-calendar-routing.module';
import { UserCalendarComponent } from './user-calendar.component';
import { UserCalendarRoomComponent } from './user-calendar-room/user-calendar-room.component';
import {FullCalendarModule} from "@fullcalendar/angular";


@NgModule({
  declarations: [
    UserCalendarComponent,
    UserCalendarRoomComponent
  ],
    imports: [
        CommonModule,
        UserCalendarRoutingModule,
        FullCalendarModule
    ]
})
export class UserCalendarModule { }
