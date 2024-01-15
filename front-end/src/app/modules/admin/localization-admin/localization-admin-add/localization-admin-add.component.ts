import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {MapImageDTOModel} from "../../../../core/service/localization/models/MapImageDTO-model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";

@Component({
  selector: 'app-localization-admin-add',
  templateUrl: './localization-admin-add.component.html',
  styleUrls: ['./localization-admin-add.component.scss']
})
export class LocalizationAdminAddComponent implements OnInit {

  localizationForm!: FormGroup;
  mapImageSimpleList!: Array<MapImageResponseModel>;

  selectedMApImage: MapImageDTOModel;
  private selectedMapImageSimple: MapImageResponseModel;

  constructor(
    private formBuilder: FormBuilder,
    private localizationService: LocalizationService,
    private mapImageService: MapImageService,
    private router: Router,
    private location: Location,
    private _snackBar: MatSnackBar,

  ) {
  }

  ngOnInit(): void {
    this.getSimpleListMapImage();

    this.localizationForm = this.formBuilder.group(
      {
        room:['', {validators:[Validators.required]}],
        coordinateX:[0, {validators:[Validators.required]}],
        coordinateY:[0, {validators:[Validators.required]}],
        mapImage: this.formBuilder.group(
          {
            id:[ '', {validators:[Validators.required]}],
            name:['', {validators:[Validators.required]}],
            filaName:[''],
            data:['']
          }
        )
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

  saveLocalization( ){
    this.localizationService.postLocalization(this.localizationForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar('Room Created Successfully!')

          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.text)

        }
      }
    )

  }

  getSimpleListMapImage() {
    this.mapImageService.getSimpleListMapImage().subscribe(value => {
      this.mapImageSimpleList = value
    })
  }

  getActualSelectedImage(id: number){
    this.mapImageService.getMapImage(id).subscribe(value => {this.selectedMApImage = value})

  }

  selected($event: MapImageResponseModel) {
    this.getActualSelectedImage($event.id)
    this.selectedMapImageSimple = $event
    this.localizationForm.get('mapImage.id')?.setValue(this.selectedMapImageSimple.id);
    this.localizationForm.get('mapImage.name')?.setValue(this.selectedMapImageSimple.name);
    this.localizationForm.get('mapImage.data')?.setValue(this.selectedMApImage.data);

  }

  protected readonly Number = Number;
}
