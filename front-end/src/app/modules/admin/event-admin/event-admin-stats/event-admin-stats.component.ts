import {Component, OnInit} from '@angular/core';
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventStatisticModel} from "../../../../core/service/event/models/EventStatistic-model";

@Component({
  selector: 'app-event-admin-stats',
  templateUrl: './event-admin-stats.component.html',
  styleUrls: ['./event-admin-stats.component.scss']
})
export class EventAdminStatsComponent implements OnInit{

  eventStatistic: EventStatisticModel = new EventStatisticModel();

  constructor(
    private eventEntityService: EventEntityService
  ) {
  }

  ngOnInit(): void {
    this.getEventStatistic();
  }

  getEventStatistic(){
    this.eventEntityService.getStatistic().subscribe(value => {
      this.eventStatistic = value;
    })
  }

}
