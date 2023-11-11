import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {MatDialog} from "@angular/material/dialog";
import {BasketService} from "../../../../core/service/booking/basket.service";
import {concat, map} from "rxjs";

@Component({
  selector: 'app-user-booking-event-list',
  templateUrl: './user-booking-event-list.component.html',
  styleUrls: ['./user-booking-event-list.component.scss']
})
export class UserBookingEventListComponent {
  displayedColumns: string[] = ['data'];
  dataSource = new MatTableDataSource<EventEntityDTOModel>();
  abstracts: Array<AbstractDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  listOfEvents: Array<number> = new Array<number>();

  constructor(private abstractsService: AbstractsService,
              private basketService: BasketService,
              private eventService: EventEntityService,
              public dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.getEventsList()
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  getEventsList() {

    this.basketService.getListForUserBasket().pipe(
      map(items=> items.map(item => {
         return this.listOfEvents.push(item.eventId)
      }) )).subscribe(
        value => {
          this.eventService.getListUserEvent(value).subscribe(value1 => {

            value1.forEach(value2 => this.dataSource.data.push(value2))

          })
        }
    )



  }





}
