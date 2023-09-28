import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DepartmentService} from "../../../../core/service/customers/department.service";
import {MatTableDataSource} from "@angular/material/table";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {DepartmentDTOModel} from "../../../../core/service/customers/models/DepartmentDTO-model";

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit, AfterViewInit{



  displayedColumns: string[] = ['id', 'name', 'street', 'buildingNumber','flatNumber', 'city','postalCode','country','institution','option'];
  dataSource = new MatTableDataSource<DepartmentDTOModel>();
  departments: Array<DepartmentDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private departmentService: DepartmentService,
  ) {
  }

  getDepartments(){
    this.departmentService.getDepartmentList().subscribe(value => {
      this.dataSource.data = value
    })
  }

  ngOnInit(): void {
    this.getDepartments()
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
}
