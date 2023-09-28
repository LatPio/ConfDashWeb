import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.scss']
})
export class CustomersListComponent implements AfterViewInit, OnInit{

  displayedColumns: string[] = ['id', 'degree', 'firstName', 'lastName','email', 'authID','phoneNumber','option'];
  dataSource = new MatTableDataSource<CustomerAdminDTOModel>();
  customers: Array<CustomerAdminDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private customerService: CustomersService,

  ) {
  }

  getCustomers(){
    this.customerService.getCustomersAdminList().subscribe(customers => this.dataSource.data = customers)
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.getCustomers()
  }

}
