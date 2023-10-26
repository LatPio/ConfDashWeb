import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {InformationLinksUserDTOModel} from "../../../../core/service/customers/models/InformationLinksUserDTOModel";
import {DepartmentDTOModel} from "../../../../core/service/customers/models/DepartmentDTO-model";
import {ProfilePhotoDTOModel} from "../../../../core/service/customers/models/ProfilePhotoDTO-model";
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
        firstName: [''],
        lastName: [''],
        email: [''],
        degree: [''],
        phoneNumber: [''],
        authID: [''],
        links: [''],
        department: [''],
        photo: [''],
      }
    );

    this.getPersonalInfo()

  }


  getPersonalInfo(){
    this.userService.getPersonalInfo().subscribe(value =>
    {
      this.personalInfoForm = this.formBuilder.group(
        {
          id:[{value: value.id, disabled:true}],
          firstName: [value.firstName],
          lastName: [value.lastName],
          email: [{value: value.email, disabled:true}],
          degree: [value.degree],
          phoneNumber: [value.phoneNumber],
          authID: [value.authID],
          links: [value.links],
          department: [value.department],
          photo: [value.photo],
        }
      )
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
}
