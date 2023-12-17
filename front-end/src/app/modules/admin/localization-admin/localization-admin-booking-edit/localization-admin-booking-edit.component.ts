import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationLightDTOModel} from "../../../../core/service/localization/models/LocalizationLightDTO-model";
import {BookingService} from "../../../../core/service/localization/booking.service";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
// import * as _moment from "moment/moment";
import {ActivatedRoute} from "@angular/router";
// const moment = _moment;
@Component({
  selector: 'app-localization-admin-booking-edit',
  templateUrl: './localization-admin-booking-edit.component.html',
  styleUrls: ['./localization-admin-booking-edit.component.scss']
})
export class LocalizationAdminBookingEditComponent implements OnInit{


  bookingForm!: FormGroup
  localizationLightList!: Array<LocalizationLightDTOModel>
  bookingId: number
  selectedLocalization: LocalizationLightDTOModel;

  constructor(
    private bookingService: BookingService,
    private localizationService: LocalizationService,
    private formBuilder: FormBuilder,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute,


  ) {

    this.bookingId = this.route.snapshot.params['bookingID'];

  }

  ngOnInit(): void {

    this.getLocalizationLightList();

    this.bookingForm = this.formBuilder.group(
      {
        id:[''],
        eventIDData:['', {validators:[Validators.required]}],
        dateStart:["", {validators:[Validators.required]}],
        dateEnd:["", {validators:[Validators.required]}],
        // eventTime:[10, {validators:[Validators.required]}],
        locationConflict:[false, {validators:[Validators.required]}],
        timeConflict:[false, {validators:[Validators.required]}],
        localization: this.formBuilder.group(
          {
            id:['', {validators:[Validators.required]}],
            room:['']
          }
        ),

      }
    );

    this.getBooking()


  }

  getBooking(){
    this.bookingService.getBooking(this.bookingId).subscribe(value => {
      this.bookingForm = this.formBuilder.group(
        {
          id:[{value: value.id , disabled: true}],
          eventIDData:[value.eventIDData, {validators:[Validators.required]}],
          dateStart:[value.dateStart, {validators:[Validators.required]}],
          dateEnd:[value.dateEnd, {validators:[Validators.required]}],
          locationConflict:[value.locationConflict, {validators:[Validators.required]}],
          timeConflict:[value.timeConflict, {validators:[Validators.required]}],
          localization: this.formBuilder.group(
            {
              id:[value.localization.id, {validators:[Validators.required]}],
              room:[value.localization.room, {validators:[Validators.required]}]
            }
          ),
        }
      )
    })
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
  updateBooking( ){

    // let data = this.bookingForm;
    // data.controls['eventTime'].setValue(this.bookingForm.get('eventTime')?.value * 60)

    this.bookingService.putBooking(this.bookingForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Updated Booked Room Successfully!")

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.detail)

        }
      }
    )

  }
}
