import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-event-admin-type-list',
  templateUrl: './event-admin-type-list.component.html',
  styleUrls: ['./event-admin-type-list.component.scss']
})
export class EventAdminTypeListComponent implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['id', 'name', 'timeInMinutes', 'locationConflict', 'timeConflict','option'];
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
    private eventTypeService: EventTypeService,
    private _snackBar: MatSnackBar

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

  openSnackBarError(message: string) {
    this._snackBar.openFromComponent(SnackbarErrorComponent, {
      data: message,
    });
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackbarMessageComponent, {
      data: message,
    });
  }

  deleteEventType(eventTypeId: number){
    this.eventTypeService.deleteEventType(eventTypeId).subscribe(
      {
        next: ()=>{
          this.openSnackBar("Event Type Deleted Successfully!")

          this.getEventType();
        },
        error: err => {
          this.openSnackBarError("This Event Type is In USE")

        }
      }
    )
  }
}
