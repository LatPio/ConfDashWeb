import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-abstract-admin-accepted',
  templateUrl: './abstract-admin-accepted.component.html',
  styleUrls: ['./abstract-admin-accepted.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AbstractAdminAcceptedComponent implements AfterViewInit, OnInit{

  columnsToDisplay : string[] = ['id', 'abstractTitle', 'authors','affiliation'];
  columnsToDisplayWithExpand =[...this.columnsToDisplay, 'expand'];
  dataSource = new MatTableDataSource<AbstractDTOModel>();
  abstracts: Array<AbstractDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  expandedElement: AbstractDTOModel;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.getAbstracts();
  }

  constructor(private abstractsService: AbstractsService,
              public dialog: MatDialog) {
  }

  getAbstracts(){
    this.abstractsService.getAbstractListAdminAccepted(true).subscribe( abstracts=>{
      this.dataSource.data = abstracts
    })
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  assignToEvent() {

  }
}
