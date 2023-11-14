import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";

@Component({
  selector: 'app-customer-admin-list-light',
  templateUrl: './customer-admin-list-light.component.html',
  styleUrls: ['./customer-admin-list-light.component.scss']
})
export class CustomerAdminListLightComponent {

  @Input() set customerListLight(value: Array<CustomerAdminDTOModel>){
    this.dataSource = new MatTableDataSource<CustomerAdminDTOModel>(value)
    this.dataSource.paginator = this.paginator;
  }
  @Output() selectedCustomer = new EventEmitter<CustomerAdminDTOModel>;
  @Output() refresh = new EventEmitter;

  dataSource: MatTableDataSource<CustomerAdminDTOModel>;
  displayedColumns: string[] = ['id', 'name', 'email','authID',];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  select(customer: CustomerAdminDTOModel) {
    this.selectedCustomer.emit(customer);
  }
}
