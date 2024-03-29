import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationLightDTOModel} from "../../../../core/service/localization/models/LocalizationLightDTO-model";
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";
import {AbstractLightDTOModel} from "../../../../core/service/abstracts/models/AbstractLightDTO-model";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-event-admin-update',
  templateUrl: './event-admin-update.component.html',
  styleUrls: ['./event-admin-update.component.scss']
})
export class EventAdminUpdateComponent implements OnInit{

  eventFrom!: FormGroup;
  eventID: number;

  localizationLightList!: Array<LocalizationLightDTOModel>
  eventTypeLightList!: Array<EventTypeDTOModel>
  abstractLightList!: Array<AbstractLightDTOModel>
  selectedLocalization: LocalizationLightDTOModel;
  selectedEventType: EventTypeDTOModel;
  selectedAbstract: AbstractLightDTOModel;

  nameOfEventType: string;
  authorsName: string;
  abstractTitle: string;

  constructor(
    private formBuilder: FormBuilder,
    private eventService: EventEntityService,
    private localizationService: LocalizationService,
    private  eventTypeService: EventTypeService,
    private abstractService: AbstractsService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute,


  ) {
    this.eventID = this.route.snapshot.params['eventID']
  }

  ngOnInit(): void {
    this.getLocalizationLightList();
    this.getEventTypeLightList();
    this.getAbstractTypeLightList();

    this.eventFrom = this.formBuilder.group(
      {
        id:[''],
        name: ['', {validators:[Validators.required]}],
        ownerId:[''],
        abstractId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        abstractName:[''],
        localizationId: [{value: '', disabled: true}, {validators:[Validators.required]}],
        localizationName: [{value: '', disabled: true}, {validators:[Validators.required]}],
        startOfEvent: [new Date(), {validators:[Validators.required]}],
        endOfEvent: [new Date(), {validators:[Validators.required]}],
        bookingId:[''],
        eventType: this.formBuilder.group(
          {
            id: ['', {validators:[Validators.required]}],
            name: [{value: '', disabled: true}, {validators:[Validators.required]}],
            timeInMinutes:[''],
            locationConflict:[''],
            timeConflict:['']
          }
        ),
      }
    );
    this.getEvent();
  }

  generateEventName(){
    if(this.authorsName && this.nameOfEventType && this.abstractTitle){
      this.eventFrom.get('name')?.setValue( this.nameOfEventType + ": " + this.authorsName + " \"" + this.abstractTitle + "\"");
    }
  }

  getEvent(){
    this.eventService.getEvent(this.eventID).subscribe(value => {
      this.eventFrom = this.formBuilder.group(
        {
          id:[{value: value.id, disabled:true}],
          name: [value.name],
          ownerId:[value.ownerId],
          abstractName:[value.abstractName],
          abstractId: [{value: value.abstractId, disabled:true}],
          localizationId: [value.localizationId],
          localizationName: [{value: value.localizationName, disabled:true}],
          startOfEvent: [value.startOfEvent],
          endOfEvent: [value.endOfEvent],
          bookingId:[value.bookingId],
          eventType: this.formBuilder.group(
            {
              id: [value.eventType.id],
              name: [{value: value.eventType.name, disabled:true}],
              timeInMinutes:[value.eventType.timeInMinutes],
              locationConflict:[value.eventType.locationConflict],
              timeConflict:[value.eventType.locationConflict]
            }
          ),
        }
      )
    })
  }

  selectedRoom($event: LocalizationLightDTOModel) {
    this.selectedLocalization = $event
    this.eventFrom.get('localizationId')?.setValue(this.selectedLocalization.id);
    this.eventFrom.get('localizationName')?.setValue(this.selectedLocalization.room);

  }

  selectedType($event: EventTypeDTOModel) {
    this.selectedEventType = $event
    this.eventFrom.get('eventType.id')?.setValue(this.selectedEventType.id);
    this.eventFrom.get('eventType.name')?.setValue(this.selectedEventType.name);
    this.nameOfEventType = this.selectedEventType.name;
    this.generateEventName();

  }

  selectedAbstractEmit($event: AbstractLightDTOModel) {
    this.selectedAbstract = $event;
    this.eventFrom.get('abstractId')?.setValue(this.selectedAbstract.id);
    this.abstractTitle = this.selectedAbstract.abstractTitle;
    this.authorsName = this.selectedAbstract.authors;
    this.generateEventName();
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

  updateEvent( ){
    this.eventService.putEvent(this.eventFrom.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Event Created Successfully!")

          this.location.back();
        },
        error: err => {
          if(err.error.text === "Event created successfully"){
            this.openSnackBar("Event created successfully")

            this.location.back();
          } else {

            this.openSnackBarError(err.error.text)
          }

        }
      }
    )

  }


  getLocalizationLightList(){
    this.localizationService.getListLocalizationLight().subscribe(value => {
      this.localizationLightList = value;
    })
  }

  getEventTypeLightList() {
    this.eventTypeService.getListEventType().subscribe(value => {
      this.eventTypeLightList = value;
    })
  }

  getAbstractTypeLightList() {
    this.abstractService.getAbstractLightListAdmin().subscribe(value => {
      this.abstractLightList = value;
    })
  }
}
