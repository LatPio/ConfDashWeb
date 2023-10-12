import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-event-admin-type-update',
  templateUrl: './event-admin-type-update.component.html',
  styleUrls: ['./event-admin-type-update.component.scss']
})
export class EventAdminTypeUpdateComponent{

  eventTypeForm: FormGroup;
  eventTypeId: number;
  constructor(
    private formBuilder: FormBuilder,
    private eventTyeService: EventTypeService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute,

  ) {
    this.eventTypeId = this.route.snapshot.params['eventTypeID']
  }
  ngOnInit(): void {
    this.eventTypeForm = this.formBuilder.group(
      {
        id:[''],
        name:['',{validators:[Validators.required]}],
        time:['',{validators:[Validators.required]}],
        locationConflict: [false,{validators:[Validators.required]}],
        timeConflict: [false,{validators:[Validators.required]}],
      }
    )
    this.getEventType();

  }

  getEventType(){
    this.eventTyeService.getEventType(this.eventTypeId).subscribe(value =>
    {
      this.eventTypeForm = this.formBuilder.group(
        {
          id:[{value: value.id, disabled:true} ],
          name:[value.name],
          time:[value.time],
          locationConflict: [value.locationConflict],
          timeConflict: [value.timeConflict],
        }
      )
    })
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

  saveEventType( ){
    this.eventTyeService.putEventType(this.eventTypeForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Event Type Updated Successfully!")

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.detail)
        }
      }
    )

  }

}
