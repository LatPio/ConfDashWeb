import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../../../core/service/localization/booking.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-localization-admin-booking-add',
  templateUrl: './localization-admin-booking-add.component.html',
  styleUrls: ['./localization-admin-booking-add.component.scss']
})
export class LocalizationAdminBookingAddComponent implements OnInit{
  bookingForm!: FormGroup

  constructor(
    private bookingService: BookingService,
    private localizationService: LocalizationService,
    private formBuilder: FormBuilder,
    private location: Location,
    private _snackBar: MatSnackBar


  ) {
  }


  ngOnInit(): void {

    this.bookingForm = this.formBuilder.group(
      {
        eventIDData:['', {validators:[Validators.required]}],
        dateStart:['', {validators:[Validators.required]}],
        localization: this.formBuilder.group(
          {
            id:['', {validators:[Validators.required]}],
            room:['', {validators:[Validators.required]}]
          }
        ),
        eventTime:['', {validators:[Validators.required]}],
        locationConflict:['', {validators:[Validators.required]}],
        timeConflict:['', {validators:[Validators.required]}]

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
  saveBooking( ){
    this.bookingService.postBooking(this.bookingForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Room Booked Successfully!")

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.detail)

        }
      }
    )

  }

}
