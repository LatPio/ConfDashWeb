import {Component, ViewChild} from '@angular/core';
import {MapImageWithRoomsDTOModel} from "../../../../core/service/localization/models/MapImageWithRoomsDTO-model";
import {MatTableDataSource} from "@angular/material/table";
import {
  LocalizationWithOutMapDTOModel
} from "../../../../core/service/localization/models/LocalizationWithOutMapDTO-model";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MapImageService} from "../../../../core/service/localization/map-image.service";
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-map-view',
  templateUrl: './user-map-view.component.html',
  styleUrls: ['./user-map-view.component.scss']
})
export class UserMapViewComponent {
  mapImageData!: MapImageWithRoomsDTOModel;
  mapImageId: number;

  displayedColumns: string[] = ['room', 'option'];
  dataSource = new MatTableDataSource<LocalizationWithOutMapDTOModel>();
  localizations: Array<LocalizationDTOModel> = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  // localizationData: LocalizationWithOutMapDTOModel;

  coordinateX:number = 0;
  coordinateY:number = 0;


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
    this.getMapImageWithRooms()
  }
  ngOnInit(): void {
    this.getMapImageWithRooms()
  }

  getMapImageWithRooms(){
    this.mapImageService.getMapImageWithRooms(this.mapImageId).subscribe(value => {
      this.mapImageData = value;
      this.dataSource.data = value.localization;
      // this.localizationData = value.localization[0];
    })
  }

  assignCoordinates(row: LocalizationWithOutMapDTOModel){
    this.coordinateX = row.coordinateX;
    this.coordinateY = row.coordinateY;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
