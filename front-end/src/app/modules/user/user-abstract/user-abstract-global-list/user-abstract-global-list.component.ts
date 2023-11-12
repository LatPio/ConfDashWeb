import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {MatDialog} from "@angular/material/dialog";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";

@Component({
  selector: 'app-user-abstract-global-list',
  templateUrl: './user-abstract-global-list.component.html',
  styleUrls: ['./user-abstract-global-list.component.scss']
})
export class UserAbstractGlobalListComponent implements AfterViewInit, OnInit{

  displayedColumns: string[] = ['data'];
  dataSource = new MatTableDataSource<EventEntityDTOModel>();
  abstracts: Array<AbstractDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private abstractsService: AbstractsService,
              private eventService: EventEntityService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getEvents()
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  applyFilter(event: Event) {
    this.dataSource.filterPredicate = (data: any, filter) => {
      const dataStr =JSON.stringify(data).toLowerCase();
      return dataStr.indexOf(filter) != -1;
    }
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  private getEvents() {
    this.eventService.getListEvent().subscribe(value => {
      this.dataSource.data = value
    })
  }
}
