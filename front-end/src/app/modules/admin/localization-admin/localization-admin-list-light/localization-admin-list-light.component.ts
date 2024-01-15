import { Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {LocalizationLightDTOModel} from "../../../../core/service/localization/models/LocalizationLightDTO-model";

@Component({
  selector: 'app-localization-admin-list-light',
  templateUrl: './localization-admin-list-light.component.html',
  styleUrls: ['./localization-admin-list-light.component.scss']
})
export class LocalizationAdminListLightComponent {


  @Input() set localizationLightList(value:Array<LocalizationLightDTOModel>){
    this.dataSource = new MatTableDataSource<LocalizationLightDTOModel>(value)
    this.dataSource.paginator = this.paginator;

  };
  @Output() selectedRoom = new EventEmitter<LocalizationLightDTOModel>;
  @Output() refresh = new EventEmitter;

  dataSource: MatTableDataSource<LocalizationLightDTOModel>;
  displayedColumns: string[] = ['id', 'name'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  select(room: LocalizationLightDTOModel) {
    this.selectedRoom.emit(room);
  }

}
