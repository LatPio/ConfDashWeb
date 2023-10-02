import {AfterViewInit, Component, EventEmitter, Input, Output} from '@angular/core';
import {MapImageResponseModel} from "../../../../core/service/localization/models/MapImageResponse-model";
import {MatTableDataSource} from "@angular/material/table";
import {InstitutionDTOModel} from "../../../../core/service/customers/models/InstitutionDTO-model";

@Component({
  selector: 'app-map-image-simple-list',
  templateUrl: './map-image-simple-list.component.html',
  styleUrls: ['./map-image-simple-list.component.scss']
})
export class MapImageSimpleListComponent implements AfterViewInit{


  @Input() set mapImageList(value:Array<MapImageResponseModel>){
    this.dataSource = new MatTableDataSource<MapImageResponseModel>(value)
  };
  @Output() selectedMapImage = new EventEmitter<MapImageResponseModel>;
  @Output() refresh = new EventEmitter;

  dataSource = new MatTableDataSource<MapImageResponseModel>();

  displayedColumns: string[] = ['id', 'name'];

  ngAfterViewInit(): void {
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  select(mapImage: MapImageResponseModel) {
    this.selectedMapImage.emit(mapImage);
  }
}
