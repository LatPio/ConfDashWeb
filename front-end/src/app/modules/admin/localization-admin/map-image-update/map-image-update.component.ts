import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-map-image-update',
  templateUrl: './map-image-update.component.html',
  styleUrls: ['./map-image-update.component.scss']
})
export class MapImageUpdateComponent implements OnInit{

  mapImageForm!: FormGroup;
  file!: File;
  mapImageId: number;
  constructor(
    private formBuilder: FormBuilder,
    private mapImageService: MapImageService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar,

    private location: Location
  ) {
    this.mapImageId = this.route.snapshot.params['mapId'];

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
    this.mapImageService.getMapImage(this.mapImageId).subscribe(value => {
      this.mapImageForm = this.formBuilder.group(
        {
          id:[{value: value.id, disabled:true}],
          name:[value.name],
          fileName:[value.fileName],
          data:[value.data]
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

  updateLocalization() {
    this.mapImageService.putMapImage(this.file, this.mapImageForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar('Map Image Updated Successfully!')

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.text)

        }
      }
    )

  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }

}
