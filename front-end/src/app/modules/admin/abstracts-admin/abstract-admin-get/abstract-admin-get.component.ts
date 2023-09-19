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




}
