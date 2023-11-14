import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-event-admin-list-light',
  templateUrl: './event-admin-list-light.component.html',
  styleUrls: ['./event-admin-list-light.component.scss']
})
export class EventAdminListLightComponent {

  @Input() set eventEntityListLight(value: Array<EventEntityDTOModel>){
    this.dataSource = new MatTableDataSource<EventEntityDTOModel>(value)
    this.dataSource.paginator = this.paginator;
  }
  @Output() selectedEvent = new EventEmitter<EventEntityDTOModel>;
  @Output() refresh = new EventEmitter;

  dataSource: MatTableDataSource<EventEntityDTOModel>;
  displayedColumns: string[] = ['id', 'name', 'abstractName', 'eventType'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  select(eventEntity: EventEntityDTOModel) {
    this.selectedEvent.emit(eventEntity);
  }

}
