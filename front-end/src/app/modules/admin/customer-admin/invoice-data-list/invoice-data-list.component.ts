import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {InvoiceDataService} from "../../../../core/service/customers/invoice-data.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {InvoiceDataDTOModel} from "../../../../core/service/customers/models/InvoiceDataDTOModel";

@Component({
  selector: 'app-invoice-data-list',
  templateUrl: './invoice-data-list.component.html',
  styleUrls: ['./invoice-data-list.component.scss']
})
export class InvoiceDataListComponent implements OnInit, AfterViewInit{



  displayedColumns: string[] = ['id', 'name', 'street', 'buildingNumber','flatNumber', 'city','postalCode','country','institution','institutionShortName', 'taxIdentificationNumber'];
  dataSource = new MatTableDataSource<InvoiceDataDTOModel>();
  departments: Array<InvoiceDataDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private invoiceDataService: InvoiceDataService,
  ) {
  }

  getDepartments(){
    this.invoiceDataService.getInvoiceList().subscribe(value => {
      this.dataSource.data = value
    })
  }

  ngOnInit(): void {
    this.getDepartments()
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
