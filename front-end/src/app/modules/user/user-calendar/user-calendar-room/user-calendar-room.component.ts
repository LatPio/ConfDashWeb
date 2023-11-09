import {Component, OnInit} from '@angular/core';
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {ActivatedRoute} from "@angular/router";
import {map, tap} from "rxjs";
import {CalendarModel} from "../Calendar-model";
import {CalendarOptions, EventInput} from "@fullcalendar/core";
import timeGridPlugin from "@fullcalendar/timegrid";

@Component({
  selector: 'app-user-calendar-room',
  templateUrl: './user-calendar-room.component.html',
  styleUrls: ['./user-calendar-room.component.scss']
})
export class UserCalendarRoomComponent implements OnInit{

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
    this.eventService.getListEventByLocation(this.roomId).pipe(
      map((items)=> items
        .map(value => (
          {
            title: value.name,
            start: value.dateTimeOfEvent,

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
