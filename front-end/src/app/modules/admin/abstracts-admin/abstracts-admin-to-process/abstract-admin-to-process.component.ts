import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {MatDialog} from "@angular/material/dialog";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {AbstractBlockDTOModel} from "../../../../core/service/abstracts/models/abstractBlockDTO-model";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-abstracts-admin-to-process',
  templateUrl: './abstract-admin-to-process.component.html',
  styleUrls: ['./abstract-admin-to-process.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0px'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AbstractAdminToProcessComponent implements AfterViewInit, OnInit{

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
              private router: Router,
              private location: Location,
              public dialog: MatDialog) {
  }

  getAbstracts(){
    this.abstractsService.getAbstractListAdminAccepted(false).subscribe( abstracts=>{
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

  accept(id: number) {
    let abstractBlock = new AbstractBlockDTOModel;
    abstractBlock.id = id;
    abstractBlock.accepted= true
    this.abstractsService.putAbstractBlockAdmin(abstractBlock).subscribe(() =>
      this.router.navigate(["/admin/abstracts/pending"]).then(()=> window.location.reload())   )
  }
}
