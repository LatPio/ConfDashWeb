import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {AbstractLightDTOModel} from "../../../../core/service/abstracts/models/AbstractLightDTO-model";

@Component({
  selector: 'app-abstract-admin-accepted-list',
  templateUrl: './abstract-admin-accepted-list.component.html',
  styleUrls: ['./abstract-admin-accepted-list.component.scss']
})
export class AbstractAdminAcceptedListComponent {
  @Input() set abstractAcceptedLightList(value: Array<AbstractLightDTOModel>){
    this.dataSource = new MatTableDataSource<AbstractLightDTOModel>(value)
    this.dataSource.paginator = this.paginator;
  }


  @Output() selectedAbstract = new EventEmitter<AbstractLightDTOModel>;
  @Output() refresh = new EventEmitter;

  dataSource: MatTableDataSource<AbstractLightDTOModel>;
  displayedColumns: string[] = ['authors','abstractTitle'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  applyFilter(eventType: Event) {
    const filterValue = (eventType.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  select(abstract: AbstractLightDTOModel) {
    this.selectedAbstract.emit(abstract);
  }
}
