import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {Router} from "@angular/router";
import { Location } from '@angular/common';
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-abstract-admin-add',
  templateUrl: './abstract-admin-add.component.html',
  styleUrls: ['./abstract-admin-add.component.scss']
})
export class AbstractAdminAddComponent implements  OnInit{

  selectedCustomer: CustomerAdminDTOModel;
  customerLightList!: Array<CustomerAdminDTOModel>;

  abstractForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private abstractService: AbstractsService,
    private router: Router,
    private location: Location,
    private customerService: CustomersService,
    private _snackBar: MatSnackBar,


  ) {
  }

  ngOnInit(): void {
    this.getCustomerList();

    this.abstractForm = this.formBuilder.group(
      {
        abstractTitle: ['', {validators:[Validators.required]}],
        body: ['', {validators:[Validators.required]}],
        authors: ['', {validators:[Validators.required]}],
        affiliation: ['', {validators:[Validators.required]}],
        ownerId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        authId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        accepted: [false, {validators:[Validators.required]}],
        // files: [''],
      }
    )
  }

  getSelectedCustomer($event: CustomerAdminDTOModel){
    this.selectedCustomer = $event;
    this.abstractForm.get('ownerId')?.setValue(this.selectedCustomer.id);
    this.abstractForm.get('authId')?.setValue(this.selectedCustomer.authID);
  }


  getCustomerList(){
    this.customerService.getCustomersAdminList().subscribe(value => {
      this.customerLightList = value;
    })
  }

  openSnackBarError(message: string) {
    this._snackBar.openFromComponent(SnackbarErrorComponent, {
      data: message,
    });
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackbarMessageComponent, {
      data: message,
    });
  }
    saveAbstract( ){
      this.abstractService.postAbstractAdmin(this.abstractForm.getRawValue()).subscribe(
        {
          next: () => {
            this.openSnackBar('Abstract Created Successfully!')

            this.location.back();
          },
          error: err => {
            this.openSnackBarError(err.error.text)
          }
        }
      )

    }

  config: any = {
    placeholder: '',
    tabsize: 2,
    height: '200px',
    uploadImagePath: '/api/upload',
    toolbar: [
      ['misc', [ 'undo', 'redo']],
      ['style', ['bold', 'italic', 'underline', 'clear']],
      ['font', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
      ['fontsize', ['fontname', 'fontsize', 'color']],
      ['para', ['style', 'ul', 'ol', 'paragraph', 'height']],
      ['insert', ['table', 'link', 'hr']]
    ],
    fontNames: ['Helvetica', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Roboto', 'Times']
  }
}
