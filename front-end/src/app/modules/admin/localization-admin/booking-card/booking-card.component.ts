import {Component, Input, OnInit} from '@angular/core';
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {BookingDTOModel} from "../../../../core/service/localization/models/BookingDTO-model";
import {BookingService} from "../../../../core/service/localization/booking.service";

@Component({
  selector: 'app-booking-card',
  templateUrl: './booking-card.component.html',
  styleUrls: ['./booking-card.component.scss']
})
export class BookingCardComponent implements OnInit{

  @Input() bookingId: number
  booking: BookingDTOModel

  constructor(
    private bookingService: BookingService
  ) {
  }

  ngOnInit(): void {
    this.getLocalization();
  }

  getLocalization(){
    this.bookingService.getBooking(this.bookingId).subscribe(value =>
      this.booking = value
    )
  }
}
