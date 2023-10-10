import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-event-admin-type-list-light',
  templateUrl: './event-admin-type-list-light.component.html',
  styleUrls: ['./event-admin-type-list-light.component.scss']
})
export class EventAdminTypeListLightComponent {


  @Input() set eventTypeLightList(value: Array<EventTypeDTOModel>){
    this.dataSource = new MatTableDataSource<EventTypeDTOModel>(value)
    this.dataSource.paginator = this.paginator;
  }


  @Output() selectedEventType = new EventEmitter<EventTypeDTOModel>;
  @Output() refresh = new EventEmitter;

  dataSource: MatTableDataSource<EventTypeDTOModel>;
  displayedColumns: string[] = ['name'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;



  applyFilter(eventType: Event) {
    const filterValue = (eventType.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  select(eventType: EventTypeDTOModel) {
    this.selectedEventType.emit(eventType);
  }
}
