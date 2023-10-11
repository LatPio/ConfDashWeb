import {Component, OnInit} from '@angular/core';
import {EventTypeService} from "../../../../core/service/event/event-type.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-event-admin-type-add',
  templateUrl: './event-admin-type-add.component.html',
  styleUrls: ['./event-admin-type-add.component.scss']
})
export class EventAdminTypeAddComponent implements OnInit{

  eventTypeForm:FormGroup;

  constructor(
    private formBuilder: FormBuilder,

    private eventTyeService: EventTypeService,
    private location: Location,
    private _snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {

    this.eventTypeForm = this.formBuilder.group(
      {
        name:['',{validators:[Validators.required]}],
        time:['',{validators:[Validators.required]}],
        locationConflict: [false,{validators:[Validators.required]}],
        timeConflict: [false,{validators:[Validators.required]}],
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

  saveEventType( ){
    this.eventTyeService.postEventType(this.eventTypeForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Event Type Created Successfully!")

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.detail)
        }
      }
    )

  }

}
