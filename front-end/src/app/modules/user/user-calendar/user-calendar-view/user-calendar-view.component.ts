import { Component } from '@angular/core';
import {CalendarOptions} from "@fullcalendar/core";
import dayGridPlugin from "@fullcalendar/daygrid"
import timeGridPlugin from "@fullcalendar/timegrid"
@Component({
  selector: 'app-user-calendar-view',
  templateUrl: './user-calendar-view.component.html',
  styleUrls: ['./user-calendar-view.component.scss']
})
export class UserCalendarViewComponent {

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    plugins: [timeGridPlugin],
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
      right: 'timeGridWeek,timeGridDay'
    }
  }
}
