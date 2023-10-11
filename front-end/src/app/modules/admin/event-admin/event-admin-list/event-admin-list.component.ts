import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";

@Component({
  selector: 'app-event-admin-list',
  templateUrl: './event-admin-list.component.html',
  styleUrls: ['./event-admin-list.component.scss']
})
export class EventAdminListComponent implements AfterViewInit ,OnInit{

  displayedColumns: string[] = ['id', 'name','abstractName', 'abstractId', 'localizationId', 'localizationName','eventType', 'dateTimeOfEvent','option'];
  dataSource = new MatTableDataSource<EventEntityDTOModel>();
  abstracts: Array<EventEntityDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.getEvents();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  constructor(
    private eventService: EventEntityService
  ) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getEvents() {
    this.eventService.getListEvent().subscribe(value => {
      this.dataSource.data = value;
    })
  }

  deleteEvent(eventId: number){
    this.eventService.deleteEvent(eventId).subscribe(value => {
      this.getEvents();
    })
  }
}
