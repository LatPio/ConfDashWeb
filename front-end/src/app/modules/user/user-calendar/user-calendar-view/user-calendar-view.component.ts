import { Component } from '@angular/core';
import {CalendarOptions, EventInput} from "@fullcalendar/core";
import timeGridPlugin from "@fullcalendar/timegrid"
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {ActivatedRoute} from "@angular/router";
import {map} from "rxjs";
@Component({
  selector: 'app-user-calendar-view',
  templateUrl: './user-calendar-view.component.html',
  styleUrls: ['./user-calendar-view.component.scss']
})
export class UserCalendarViewComponent {

  events: EventInput[] = [];
  roomId: number;
  constructor(
    private eventService: EventEntityService,
    private activeRoute: ActivatedRoute
  ) {
    this.roomId = this.activeRoute.snapshot.params['locationID'];

  }
  ngOnInit(): void {
    this.getEventsToCalendar()

  }


  getEventsToCalendar(){
    this.eventService.getListEvent().pipe(
      map((items)=> items
        .map(value => (
          {
            title: "Room: "+value.localizationName + ", " + value.name + ": " +value.abstractName,
            start: value.startOfEvent,
            end: value.endOfEvent
          })))
    )
      .subscribe( value => this.events = value)
  }



  calendarOptions: CalendarOptions = {
    initialView: 'timeGridDay',
    plugins: [timeGridPlugin],
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
      right: 'timeGridWeek,timeGridDay'
    },

  }
}
