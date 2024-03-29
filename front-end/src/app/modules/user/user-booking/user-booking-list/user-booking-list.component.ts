import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {BasketService} from "../../../../core/service/booking/basket.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {BasketDTOModel} from "../../../../core/service/booking/models/basketDTO-model";

@Component({
  selector: 'app-user-booking-list',
  templateUrl: './user-booking-list.component.html',
  styleUrls: ['./user-booking-list.component.scss']
})
export class UserBookingListComponent implements AfterViewInit, OnInit{

  displayedColumns: string[] = ['name', 'option'];
  dataSource = new MatTableDataSource<BasketDTOModel>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private basketService: BasketService,

  ) {
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

  }

  ngOnInit(): void {
    this.getBasket()
  }

  getBasket() {
    this.basketService.getListForUserBasket().subscribe(basket => {
      this.dataSource.data = basket;
    })
  }

  deleteBookedEvent(basketDTO: BasketDTOModel) {
    this.basketService.deleteUserBasket(basketDTO).subscribe(value => {
      this.getBasket();
    })

  }


}
