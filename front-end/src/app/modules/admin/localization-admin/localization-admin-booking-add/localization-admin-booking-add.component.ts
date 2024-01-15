import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../../../core/service/localization/booking.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import * as _moment from 'moment';
import {LocalizationLightDTOModel} from "../../../../core/service/localization/models/LocalizationLightDTO-model";
const moment = _moment;
@Component({
  selector: 'app-localization-admin-booking-add',
  templateUrl: './localization-admin-booking-add.component.html',
  styleUrls: ['./localization-admin-booking-add.component.scss']
})
export class LocalizationAdminBookingAddComponent implements OnInit{
  bookingForm!: FormGroup
  localizationLightList!: Array<LocalizationLightDTOModel>

  selectedLocalization: LocalizationLightDTOModel;

  constructor(
    private bookingService: BookingService,
    private localizationService: LocalizationService,
    private formBuilder: FormBuilder,
    private location: Location,
    private _snackBar: MatSnackBar


  ) {
  }

  ngOnInit(): void {

    this.getLocalizationLightList();

    this.bookingForm = this.formBuilder.group(
      {
        eventIDData:['', {validators:[Validators.required]}],
        dateStart:[moment(), {validators:[Validators.required]}],

        eventTime:[10, {validators:[Validators.required]}],
        locationConflict:[false, {validators:[Validators.required]}],
        timeConflict:[false, {validators:[Validators.required]}],
        localization: this.formBuilder.group(
          {
            id:['', {validators:[Validators.required]}],
            room:['']
          }
        ),

      }
    )
  }

  getLocalizationLightList(){
    this.localizationService.getListLocalizationLight().subscribe(value => {
      this.localizationLightList = value;
    })
  }

  selected($event: LocalizationLightDTOModel) {
    this.selectedLocalization = $event
    this.bookingForm.get('localization.id')?.setValue(this.selectedLocalization.id);
    this.bookingForm.get('localization.room')?.setValue(this.selectedLocalization.room);

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

    let data = this.bookingForm;
    data.controls['eventTime'].setValue(this.bookingForm.get('eventTime')?.value * 60)

    this.bookingService.postBooking(data.getRawValue()).subscribe(
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
