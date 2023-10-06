import {Component, OnInit} from '@angular/core';
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-map-image-add',
  templateUrl: './map-image-add.component.html',
  styleUrls: ['./map-image-add.component.scss']
})
export class MapImageAddComponent implements  OnInit {

  mapImageForm!: FormGroup;
  mapImageSimpleList!: Array<MapImageResponseModel>;
  file!: File;
  private snackBar: MatSnackBar;

  constructor(
    private formBuilder: FormBuilder,
    private mapImageService: MapImageService,
    private router: Router,
    private location: Location,
    private _snackBar: MatSnackBar

  ) {
  }


  ngOnInit(): void {
    this.getSimpleListMapImage();

    this.mapImageForm = this.formBuilder.group(
      {
        name:['', {validators:[Validators.required]}]
      }
    )
  }

  getSimpleListMapImage() {
    this.mapImageService.getSimpleListMapImage().subscribe(value => {
      this.mapImageSimpleList = value
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

  saveLocalization() {
    this.mapImageService.postSaveMapImage(this.file, this.mapImageForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Map added Successfully!")

          this.location.back();
        },
        error: err => {
          // console.log(err.error.detail)
          this.openSnackBarError(err.error.detail)

        }
      }
    )

  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }
}
