import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {LocalizationService} from "../../../../core/service/localization/localization.service";

@Component({
  selector: 'app-localization-admin-list',
  templateUrl: './localization-admin-list.component.html',
  styleUrls: ['./localization-admin-list.component.scss']
})
export class LocalizationAdminListComponent implements AfterViewInit ,OnInit{


  displayedColumns: string[] = ['id', 'room', 'coordinateX','coordinateY', 'mapName','option'];
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
  deleteLocalization(localizationId: number){
    this.localizationService.deleteLocalization(localizationId).subscribe(value => {
      this.getLocalization();
    })
  }

}
