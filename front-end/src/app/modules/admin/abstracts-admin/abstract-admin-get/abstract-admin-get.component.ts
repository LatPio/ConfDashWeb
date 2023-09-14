import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {DeleteDialogComponent} from "../../../shared/delete-dialog/delete-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {
  AbstractAttachmentFileAddComponent
} from "../abstract-attachment-file-add/abstract-attachment-file-add.component";

@Component({
  selector: 'app-abstract-admin-get',
  templateUrl: './abstract-admin-get.component.html',
  styleUrls: ['./abstract-admin-get.component.scss']
})
export class AbstractAdminGetComponent implements OnInit{

  // abstractForm!: FormGroup;
  abstractID: number;
  abstractData!: AbstractDTOModel;

  constructor(
    private abstractService: AbstractsService,
    // private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
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
