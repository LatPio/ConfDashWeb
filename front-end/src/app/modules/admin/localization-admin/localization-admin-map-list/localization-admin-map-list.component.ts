import {Component, OnInit} from '@angular/core';
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {MapImageDTOModel} from "../../../../core/service/localization/models/MapImageDTO-model";

@Component({
  selector: 'app-localization-admin-map-list',
  templateUrl: './localization-admin-map-list.component.html',
  styleUrls: ['./localization-admin-map-list.component.scss']
})
export class LocalizationAdminMapListComponent implements OnInit {

  mapImageListData: Array<MapImageDTOModel>;

  constructor(
    private mapImageService: MapImageService
  ) {
  }

  ngOnInit(): void {
    this.getMapImageListData();
  }

  getMapImageListData(){
    this.mapImageService.getListMapImage().subscribe(value => {
      this.mapImageListData = value
    })
  }


  deleteMApImage(id: number) {
    this.mapImageService.deleteFile(id).subscribe(
      {
        next: ()=>{
          this.getMapImageListData();
        },
        error: err => {

        }
      }
    )
  }
}
