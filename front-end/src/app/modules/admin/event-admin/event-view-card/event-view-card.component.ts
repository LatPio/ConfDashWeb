import {Component, Input, OnInit} from '@angular/core';
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";

@Component({
  selector: 'app-event-view-card',
  templateUrl: './event-view-card.component.html',
  styleUrls: ['./event-view-card.component.scss']
})
export class EventViewCardComponent implements OnInit{
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
}
