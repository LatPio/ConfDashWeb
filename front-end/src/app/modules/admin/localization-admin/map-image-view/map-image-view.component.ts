import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MapImageWithRoomsDTOModel} from "../../../../core/service/localization/models/MapImageWithRoomsDTO-model";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {ActivatedRoute, Route} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {
  LocalizationWithOutMapDTOModel
} from "../../../../core/service/localization/models/LocalizationWithOutMapDTO-model";
import {LocalizationService} from "../../../../core/service/localization/localization.service";

@Component({
  selector: 'app-map-image-view',
  templateUrl: './map-image-view.component.html',
  styleUrls: ['./map-image-view.component.scss']
})
export class MapImageViewComponent implements AfterViewInit, OnInit{

  mapImageData!: MapImageWithRoomsDTOModel;
  mapImageId: number;

  displayedColumns: string[] = ['id', 'room', 'coordinateX','coordinateY','option'];
  dataSource = new MatTableDataSource<LocalizationWithOutMapDTOModel>();
  localizations: Array<LocalizationDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  constructor(
    private mapImageService: MapImageService,
    private localizationService: LocalizationService,
    private route: ActivatedRoute,
  ) {

    this.mapImageId = this.route.snapshot.params['mapId'];

  }
  ngOnInit(): void {
    this.getMapImageWithRooms()
  }

  getMapImageWithRooms(){
    this.mapImageService.getMapImageWithRooms(this.mapImageId).subscribe(value => {
      this.mapImageData = value;
      this.dataSource.data = value.localization;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  deleteLocalization(localizationId: number){
    this.localizationService.deleteLocalization(localizationId).subscribe(value => {
      this.getMapImageWithRooms();
    })
  }

}
