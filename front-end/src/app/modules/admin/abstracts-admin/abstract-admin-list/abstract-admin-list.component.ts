import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatDialog} from "@angular/material/dialog";
import {DeleteDialogComponent} from "../../../shared/delete-dialog/delete-dialog.component";

@Component({
  selector: 'app-abstract-admin-list',
  templateUrl: './abstract-admin-list.component.html',
  styleUrls: ['./abstract-admin-list.component.scss']
})
export class AbstractAdminListComponent implements AfterViewInit ,OnInit{
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  displayedColumns: string[] = ['id', 'abstractTitle', 'body', 'authors','affiliation', 'presenterId', 'ownerId','authId', 'accepted','files','option'];
  dataSource = new MatTableDataSource<AbstractDTOModel>();
  abstracts: Array<AbstractDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private abstractsService: AbstractsService,
              public dialog: MatDialog) {
      }

  getAbstracts(){
    this.abstractsService.getAbstractListAdmin().subscribe( abstracts=>{
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

  ngOnInit(): void {
    this.getAbstracts()
  }


  deleteExpense(abstract: AbstractDTOModel) {
    this.abstractsService.deleteAbstractAdmin(abstract).subscribe(value => {
      this.getAbstracts();
    })

  }

  openDeleteDialog(abstract: AbstractDTOModel): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent,
      {
      width: '400px',
      enterAnimationDuration: 0,
      panelClass: 'customStyle',

      data: { item: abstract.abstractTitle } // Pass the item's name or details
    }
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteExpense(abstract)
        // Perform delete action
        console.log('Item deleted');
      }
    });
  }
}
