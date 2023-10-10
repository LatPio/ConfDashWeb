import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";

@Component({
  selector: 'app-event-admin-type-list',
  templateUrl: './event-admin-type-list.component.html',
  styleUrls: ['./event-admin-type-list.component.scss']
})
export class EventAdminTypeListComponent implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['id', 'name', 'time', 'locationConflict', 'timeConflict','option'];
  dataSource = new MatTableDataSource<EventTypeDTOModel>();
  abstracts: Array<EventTypeDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.getEventType();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  constructor(
    private eventTypeService: EventTypeService
  ) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getEventType() {
    this.eventTypeService.getListEventType().subscribe(value => {
      this.dataSource.data = value;
    })
  }

  deleteEventType(eventTypeId: number){
    this.eventTypeService.deleteEventType(eventTypeId).subscribe(value => {
      this.getEventType();
    })
  }
}
