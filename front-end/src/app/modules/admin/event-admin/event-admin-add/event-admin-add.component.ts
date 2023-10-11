import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LocalizationLightDTOModel} from "../../../../core/service/localization/models/LocalizationLightDTO-model";
import {EventTypeDTOModel} from "../../../../core/service/event/models/EventTypeDTO-model";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {AbstractLightDTOModel} from "../../../../core/service/abstracts/models/AbstractLightDTO-model";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";

@Component({
  selector: 'app-event-admin-add',
  templateUrl: './event-admin-add.component.html',
  styleUrls: ['./event-admin-add.component.scss']
})
export class EventAdminAddComponent implements OnInit{

  eventFrom!: FormGroup

  localizationLightList!: Array<LocalizationLightDTOModel>
  eventTypeLightList!: Array<EventTypeDTOModel>
  abstractLightList!: Array<AbstractLightDTOModel>
  selectedLocalization: LocalizationLightDTOModel;
  selectedEventType: EventTypeDTOModel;
  selectedAbstract: AbstractLightDTOModel;

  constructor(
    private formBuilder: FormBuilder,
    private eventService: EventEntityService,
    private localizationService: LocalizationService,
    private  eventTypeService: EventTypeService,
    private abstractService: AbstractsService,
    private location: Location,
    private _snackBar: MatSnackBar

  ) {
  }

  ngOnInit(): void {
    this.getLocalizationLightList();
    this.getEventTypeLightList();
    this.getAbstractTypeLightList();

    this.eventFrom = this.formBuilder.group(
      {
        name: ['', {validators:[Validators.required]}],
        abstractId: ['', {validators:[Validators.required]}],
        localizationId: ['', {validators:[Validators.required]}],
        localizationName: ['', {validators:[Validators.required]}],
        dateTimeOfEvent: [new Date(), {validators:[Validators.required]}],
        eventType: this.formBuilder.group(
          {
            id: ['', {validators:[Validators.required]}],
            name: ['', {validators:[Validators.required]}]

          }
        ),

      }
    )
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

  }

  selectedAbstractEmit($event: AbstractLightDTOModel) {
    this.selectedAbstract = $event;
    this.eventFrom.get('abstractId')?.setValue(this.selectedAbstract.id)
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

  saveEvent( ){
    this.eventService.postEvent(this.eventFrom.getRawValue()).subscribe(
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
          // this.location.back();
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
