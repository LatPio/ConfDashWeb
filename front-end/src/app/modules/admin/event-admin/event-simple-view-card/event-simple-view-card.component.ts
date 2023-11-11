import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {verifyHostBindings} from "@angular/compiler";

@Component({
  selector: 'app-event-simple-view-card',
  templateUrl: './event-simple-view-card.component.html',
  styleUrls: ['./event-simple-view-card.component.scss']
})
export class EventSimpleViewCardComponent implements OnInit, AfterViewInit{

  @Input() eventData: EventEntityDTOModel;
  @Input() eventID: number;

  constructor(
    private eventService: EventEntityService
  ) {
  }
  ngOnInit(): void {
    this.getEventEntity();
  }

  getEventEntity(){
    this.eventService.getEvent(this.eventID).subscribe(
      value => {
        this.eventData = value;
      }
    )
  }

  ngAfterViewInit(): void {
    this.getEventEntity();
  }

}
