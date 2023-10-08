import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {BookingService} from "../../../../core/service/localization/booking.service";
import {BookingDTOModel} from "../../../../core/service/localization/models/BookingDTO-model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-localization-admin-booking-list',
  templateUrl: './localization-admin-booking-list.component.html',
  styleUrls: ['./localization-admin-booking-list.component.scss']
})
export class LocalizationAdminBookingListComponent implements OnInit, AfterViewInit{

  displayedColumns: string[] = ['id', 'eventIDData', 'locationConflict','timeConflict', 'dateStart','dateEnd','localization','option'];
  dataSource = new MatTableDataSource<BookingDTOModel>();
  localizations: Array<BookingDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  ngOnInit(): void {
    this.getBookings()
  }

  constructor(
    private bookingService: BookingService
  ) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getBookings(){
    this.bookingService.getListBooking().subscribe(value => {
      this.dataSource.data = value
    })
  }
  deleteBookings(bookingId: number){
    this.bookingService.deleteLocalization(bookingId).subscribe(value => {
      this.getBookings();
    })

  }
}
