import { Component } from '@angular/core';
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-abstract-view',
  templateUrl: './user-abstract-view.component.html',
  styleUrls: ['./user-abstract-view.component.scss']
})
export class UserAbstractViewComponent {
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
