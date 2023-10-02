import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";


@Component({
  selector: 'app-localization-admin-add',
  templateUrl: './localization-admin-add.component.html',
  styleUrls: ['./localization-admin-add.component.scss']
})
export class LocalizationAdminAddComponent implements OnInit {

  localizationForm!: FormGroup;
  mapImageSimpleList!: Array<MapImageResponseModel>;

  selectedMApImage: MapImageResponseModel;
  constructor(
    private formBuilder: FormBuilder,
    private localizationService: LocalizationService,
    private mapImageService: MapImageService,
    private router: Router,
    private location: Location

  ) {
  }

  ngOnInit(): void {
    this.getSimpleListMapImage();

    this.localizationForm = this.formBuilder.group(
      {
        room:['', {validators:[Validators.required]}],
        coordinateX:['', {validators:[Validators.required]}],
        coordinateY:['', {validators:[Validators.required]}],
        mapImage: this.formBuilder.group(
          {
            id:[ '', {validators:[Validators.required]}],
            name:['', {validators:[Validators.required]}]
          }
        )
      }
    )
  }
  saveLocalization( ){
    this.localizationService.postLocalization(this.localizationForm.getRawValue()).subscribe(
      {
        next: () => {

          this.location.back();
        },
        error: err => {

        }
      }
    )

  }

  getSimpleListMapImage() {
    this.mapImageService.getSimpleListMapImage().subscribe(value => {
      this.mapImageSimpleList = value
    })
  }

  selected($event: MapImageResponseModel) {
    this.selectedMApImage = $event
    this.localizationForm.get('mapImage.id')?.setValue(this.selectedMApImage.id);
    this.localizationForm.get('mapImage.name')?.setValue(this.selectedMApImage.name);

  }
}
