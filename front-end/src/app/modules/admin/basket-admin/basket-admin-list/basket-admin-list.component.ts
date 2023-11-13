import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {BasketDTOModel} from "../../../../core/service/booking/models/basketDTO-model";
import {BasketService} from "../../../../core/service/booking/basket.service";

@Component({
  selector: 'app-basket-admin-list',
  templateUrl: './basket-admin-list.component.html',
  styleUrls: ['./basket-admin-list.component.scss']
})
export class BasketAdminListComponent {
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  displayedColumns: string[] = ['id', 'name', 'eventId','deletable', 'authId','option'];
  dataSource = new MatTableDataSource<BasketDTOModel>();
    @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private basketService: BasketService,
              public dialog: MatDialog) {
  }

  getBasketItems(){
    this.basketService.getListAllItemsAdminBasket().subscribe( items=>{
      this.dataSource.data = items
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.getBasketItems();
  }


  deleteItem(item: BasketDTOModel) {
    this.basketService.deleteAdminBasket(item).subscribe(value => {
      this.getBasketItems();
    })

  }
}
