import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {checkForPrivateExports} from "@angular/compiler-cli/src/ngtsc/entry_point";
import {BasketService} from "../../../../core/service/booking/basket.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-basket-admin-add',
  templateUrl: './basket-admin-add.component.html',
  styleUrls: ['./basket-admin-add.component.scss']
})
export class BasketAdminAddComponent implements OnInit{

  basketItemForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private basketService: BasketService,
    private location: Location,
    private _snackBar: MatSnackBar

  ) {
  }
  ngOnInit(): void {
    this.basketItemForm = this.formBuilder.group(
      {
        name:['',{validators:[Validators.required]}],
        eventId:['', {validators:[Validators.required]}],
        deletable:['', {validators:[Validators.required]}],
        authId:['', {validators:[Validators.required]}]

      }
    )
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

  saveBasketItem(){
    this.basketService.postAdminBasket(this.basketItemForm.getRawValue()).subscribe(
      {
        next: () =>{
          this.openSnackBar("Item Created Successfully!")

          this.location.back()
        },
        error: err => {
          this.openSnackBarError(err.error.text)


        }
      }
    )
  }

}
