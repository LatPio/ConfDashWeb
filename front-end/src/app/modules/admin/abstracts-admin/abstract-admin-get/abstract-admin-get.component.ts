import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatDialog} from "@angular/material/dialog";
import {AbstractsAttachmentFileService} from "../../../../core/service/abstracts/abstracts-attachment-file.service";
import {CustomerCardDTOModel} from "../../../../core/service/customers/models/CustomerCardDTO-model";
import {CustomersService} from "../../../../core/service/customers/customers.service";


@Component({
  selector: 'app-abstract-admin-get',
  templateUrl: './abstract-admin-get.component.html',
  styleUrls: ['./abstract-admin-get.component.scss']
})
export class AbstractAdminGetComponent implements OnInit{

  abstractID: number;
  abstractData: AbstractDTOModel;
  customerCard: CustomerCardDTOModel;
  filesExist: boolean = false;

  constructor(
    private abstractService: AbstractsService,
    private customerService: CustomersService,
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
      this.filesExist

      this.getCustomer(value.ownerId);
    });


  }
  getCustomer(ownerId :number){
    this.customerService.getCustomerAdmin(ownerId).subscribe(
      value => {
        this.customerCard = value
      }
    );
  }

  tryFiles(){
    this.filesExist = this.abstractData.files.length > 0;
  }
  refreshData(){
    this.getAbstract()
    window.location.reload();

  }


}
