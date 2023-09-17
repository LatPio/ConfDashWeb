import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatDialog} from "@angular/material/dialog";
import {AbstractsAttachmentFileService} from "../../../../core/service/abstracts/abstracts-attachment-file.service";


@Component({
  selector: 'app-abstract-admin-get',
  templateUrl: './abstract-admin-get.component.html',
  styleUrls: ['./abstract-admin-get.component.scss']
})
export class AbstractAdminGetComponent implements OnInit{

  abstractID: number;
  abstractData!: AbstractDTOModel;

  constructor(
    private abstractService: AbstractsService,
    private filesService: AbstractsAttachmentFileService,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    this.abstractID = this.route.snapshot.params['abstractID'];

  }

  ngOnInit(): void {
    this.getAbstract();
  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstractData = value;
    });
  }

  deleteFile(id: number){
    this.filesService.deleteFile(id).subscribe(value => {
      this.getAbstract();
    })
  }

  // openAddFileDialog(abstract: AbstractDTOModel): void {
  //   const dialogRef = this.dialog.open(AbstractAttachmentFileAddComponent,
  //     {
  //       width: '600px',
  //       enterAnimationDuration: 0,
  //       panelClass: 'customStyle',
  //
  //       data: abstract // Pass the item's name or details
  //     }
  //   );
  //
  //   dialogRef.afterClosed().subscribe(result => {
  //     if (result) {
  //       this.getAbstract()
  //       // this.deleteExpense(abstract)
  //       // Perform delete action
  //       // console.log('Item deleted');
  //     }
  //   });
  // }

}
