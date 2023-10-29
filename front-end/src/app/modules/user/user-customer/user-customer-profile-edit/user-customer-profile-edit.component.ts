import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-customer-profile-edit',
  templateUrl: './user-customer-profile-edit.component.html',
  styleUrls: ['./user-customer-profile-edit.component.scss']
})
export class UserCustomerProfileEditComponent {

  personalInfoForm!: FormGroup;

  constructor(
    private userService: CustomersService,
    private formBuilder: FormBuilder,
    private router: Router,
    private location: Location,
    private _snackBar: MatSnackBar

  ) {
    this.personalInfoForm = this.formBuilder.group(
      {
        id:[''],
        firstName: ['',{updateOn: 'blur', validators: [Validators.required]}],
        lastName: ['',{updateOn: 'blur', validators: [Validators.required]}],
        email: ['',{updateOn: 'blur', validators: [Validators.email, Validators.required]}],
        degree: ['',{updateOn: 'blur', validators: [Validators.required]}],
        phoneNumber: ['',{updateOn: 'blur', validators: [Validators.required]}],
        authID: ['',{updateOn: 'blur', validators: [Validators.required]}],
        links: ['',{updateOn: 'blur', validators: [Validators.required]}],
        // links: this.formBuilder.array([
        //     this.addLink()
        //   ]
        // ),
        invoiceData: this.formBuilder.group(
          {
            id: [''],
            name: ['',{validators: [Validators.required]}],
            street: ['',{updateOn: 'blur', validators: [Validators.required]}],
            buildingNumber: ['',{updateOn: 'blur', validators: [Validators.required]}],
            flatNumber:['',{updateOn: 'blur', validators: [Validators.required]}],
            city: ['',{updateOn: 'blur', validators: [Validators.required]}],
            postalCode: ['',{updateOn: 'blur', validators: [Validators.required]}],
            country: ['',{updateOn: 'blur', validators: [Validators.required]}],
            taxIdentificationNumber :['',{updateOn: 'blur', validators: [Validators.required]}],
            institutionShortName: ['',{validators: [Validators.required]}],
            institution:['',{updateOn: 'blur', validators: [Validators.required]}]
          }
        ),
        photo: ['']
      }
    );

    this.getPersonalInfo()

  }


  getPersonalInfo(){
    this.userService.getPersonalInfo().subscribe(value =>
    {
      this.personalInfoForm.patchValue(value)

    });
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

  updatePersonalInfo() {
      this.userService.putCustomersUSer(this.personalInfoForm.getRawValue()).subscribe(
        {
          next: () => {
            this.openSnackBar("Personal Data Successfully Updated!")

            this.location.back();
          },
          error: err => {
            // console.log(err.error.detail)
            this.openSnackBarError(err.error.detail)

          }
        }
      )
  }

  private addLink() {
    return this.formBuilder.group({
      id:[],
      name: [],
      authID:[this.personalInfoForm.get("authID")?.value],
      urlLink:[],
      customer:{
        id: [this.personalInfoForm.get("id")?.value]
      }

    })
  }
}
