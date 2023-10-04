import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {MapImageDTOModel} from "../../../../core/service/localization/models/MapImageDTO-model";

@Component({
  selector: 'app-localization-admin-edit',
  templateUrl: './localization-admin-edit.component.html',
  styleUrls: ['./localization-admin-edit.component.scss']
})
export class LocalizationAdminEditComponent {

  localizationId: number;
  localizationForm!: FormGroup;
  localizationData: LocalizationDTOModel;
  mapImageSimpleList!: Array<MapImageResponseModel>;
  selectedMApImage: MapImageDTOModel;
  private selectedMapImageSimple: MapImageResponseModel;


  constructor(
    private formBuilder: FormBuilder,
    private localizationService: LocalizationService,
    private mapImageService: MapImageService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location

  ) {
    this.localizationId = this.route.snapshot.params['localizationID'];
    this.getSimpleListMapImage();
    this.localizationForm = this.formBuilder.group(
      {
        id:[''],
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
    );
    this.getLocalization();

  }

  getLocalization(){
    this.localizationService.getLocalization(this.localizationId).subscribe(value => {
      this.localizationForm = this.formBuilder.group(
        {
          id: [{value: value.id , disabled: true}],
          room:[value.room],
          coordinateX:[value.coordinateX],
          coordinateY:[value.coordinateY],
          mapImage: this.formBuilder.group(
            {
              id:[ value.mapImage.id],
              name:[value.mapImage.name],
              filaName:[value.mapImage.fileName],
              data:[value.mapImage.data]
            }
          )}
      )
      this.localizationData = value;
      this.getActualSelectedImage(value.mapImage.id)

    });

  }

  updateLocalization(){
    this.localizationService.putLocalization(this.localizationForm.getRawValue()).subscribe(
      {
        next: ()=>{
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
  getActualSelectedImage(id: number){
    this.mapImageService.getMapImage(id).subscribe(value => {this.selectedMApImage = value})

  }

  selected($event: MapImageResponseModel) {
    // this.mapImageService.getMapImage($event.id).subscribe(value => {this.selectedMApImage = value})
    this.getActualSelectedImage($event.id)
    this.selectedMapImageSimple = $event
    this.localizationForm.get('mapImage.id')?.setValue(this.selectedMApImage.id);
    this.localizationForm.get('mapImage.name')?.setValue(this.selectedMApImage.name);
    this.localizationForm.get('mapImage.data')?.setValue(this.selectedMApImage.data);




  }
  protected readonly Number = Number;
}
