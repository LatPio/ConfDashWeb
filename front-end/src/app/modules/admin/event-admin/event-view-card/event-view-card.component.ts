import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {BasketService} from "../../../../core/service/booking/basket.service";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-event-view-card',
  templateUrl: './event-view-card.component.html',
  styleUrls: ['./event-view-card.component.scss']
})
export class EventViewCardComponent implements OnInit{
  @Input() eventData: EventEntityDTOModel;
  @Input() eventID: number;

  constructor(
    private eventService: EventEntityService,
    private basketService: BasketService,
    private _snackBar: MatSnackBar

  ) {
  }
  ngOnInit(): void {
    this.getEventEntity();
  }

  getEventEntity(){
    this.eventService.getEvent(this.eventID).subscribe(
      value => {
        this.eventData = value;
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

  postEventToBasket(event: EventEntityDTOModel){
    this.basketService.postUserBasket(event).subscribe({
        next: () => {
          this.openSnackBar("Event Saved Successfully!")

        },
        error: err => {
          this.openSnackBarError(err.error.detail)

        }
      }
    )
  }
}
