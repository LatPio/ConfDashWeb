import {Component, OnInit} from '@angular/core';
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {ActivatedRoute} from "@angular/router";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";

@Component({
  selector: 'app-event-admin-view',
  templateUrl: './event-admin-view.component.html',
  styleUrls: ['./event-admin-view.component.scss']
})
export class EventAdminViewComponent implements OnInit{
  eventEntity: EventEntityDTOModel;
  evenId: number;

  constructor(
    private eventService: EventEntityService,
    private route: ActivatedRoute,

  ) {
    this.evenId = this.route.snapshot.params['eventID']

  }


  ngOnInit(): void {
    this.getEvent();
  }


  getEvent(){
    this.eventService.getEvent(this.evenId).subscribe(value => {
      this.eventEntity = value;
    })
  }
}
