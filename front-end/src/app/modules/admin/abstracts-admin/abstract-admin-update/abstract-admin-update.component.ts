import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute, Router} from "@angular/router";
import { Location } from '@angular/common';
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-abstract-admin-update',
  templateUrl: './abstract-admin-update.component.html',
  styleUrls: ['./abstract-admin-update.component.scss']
})
export class AbstractAdminUpdateComponent {

  selectedCustomer: CustomerAdminDTOModel;
  customerLightList!: Array<CustomerAdminDTOModel>;

  abstractForm!: FormGroup;
  abstractID: number;

  constructor(
    private abstractService: AbstractsService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private customerService: CustomersService,
    private _snackBar: MatSnackBar,
  ) {
    this.abstractID = this.route.snapshot.params['abstractID'];
    this.getCustomerList();

    this.abstractForm = this.formBuilder.group(
      {
        id:[{value: '', disabled: true}, {validators:[Validators.required]}],
        abstractTitle: ['', {validators:[Validators.required]}],
        body: ['', {validators:[Validators.required]}],
        authors: ['', {validators:[Validators.required]}],
        affiliation: ['', {validators:[Validators.required]}],
        ownerId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        authId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        accepted: [false, {validators:[Validators.required]}],
        files: [''],
        comments: ['']
      }
    );
    this.getAbstract();

  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstractForm = this.formBuilder.group(
        {
          id:[{value: this.abstractID, disabled:true}],
          abstractTitle: [value.abstractTitle ],
          body: [value.body],
          authors: [value.authors],
          affiliation: [value.affiliation],
          ownerId: [{value: value.ownerId, disabled:true}],
          authId: [{value: value.authId, disabled:true}],
          accepted: [value.accepted],
          files: [value.files],
          comments: [value.comments]
        }
      )
      this.getCustomer(value.ownerId);
    })

  }

  getCustomer(customerID: number){
    this.customerService.getCustomerAdmin(customerID).subscribe(value => {
      this.selectedCustomer = value
    })
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

  updateAbstract() {
    console.log(this.abstractForm.getRawValue())
    this.abstractService.putAbstractAdmin(this.abstractForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar('Abstract Updated Successfully!')
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
