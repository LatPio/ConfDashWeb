import {Component, OnInit} from '@angular/core';
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MapImageDTOModel} from "../../../../core/service/localization/models/MapImageDTO-model";

@Component({
  selector: 'app-map-image-add',
  templateUrl: './map-image-add.component.html',
  styleUrls: ['./map-image-add.component.scss']
})
export class MapImageAddComponent implements  OnInit {

  mapImageForm!: FormGroup;
  mapImageSimpleList!: Array<MapImageResponseModel>;
  file!: File;

  constructor(
    private formBuilder: FormBuilder,
    // private localizationService: LocalizationService,
    private mapImageService: MapImageService,
    private router: Router,
    private location: Location
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


  saveLocalization() {
    this.mapImageService.postSaveMapImage(this.file, this.mapImageForm.getRawValue()).subscribe(
      {
        next: () => {

          this.location.back();
        },
        error: err => {

        }
      }
    )

  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }
}
