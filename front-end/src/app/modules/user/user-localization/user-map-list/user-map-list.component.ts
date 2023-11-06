import {Component, OnInit} from '@angular/core';
import {MapImageDTOModel} from "../../../../core/service/localization/models/MapImageDTO-model";
import {MapImageService} from "../../../../core/service/localization/map-image.service";

@Component({
  selector: 'app-user-map-list',
  templateUrl: './user-map-list.component.html',
  styleUrls: ['./user-map-list.component.scss']
})
export class UserMapListComponent  implements OnInit{

  mapImageListData: Array<MapImageDTOModel>;

  constructor(
    private mapImageService: MapImageService,
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
}
