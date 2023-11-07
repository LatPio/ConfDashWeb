import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {LocalizationService} from "../../../../core/service/localization/localization.service";

@Component({
  selector: 'app-user-localization-list',
  templateUrl: './user-localization-list.component.html',
  styleUrls: ['./user-localization-list.component.scss']
})
export class UserLocalizationListComponent implements AfterViewInit, OnInit{

  displayedColumns: string[] = ['id', 'room', 'mapName','option'];
  dataSource = new MatTableDataSource<LocalizationDTOModel>();
  localizations: Array<LocalizationDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  ngOnInit(): void {
    this.getLocalization();
  }

  constructor(
    private localizationService: LocalizationService
  ) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  getLocalization(){
    this.localizationService.getListLocalization().subscribe(value => {
      this.dataSource.data = value
    })
  }


}
