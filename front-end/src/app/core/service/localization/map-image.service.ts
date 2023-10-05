import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {Observable} from "rxjs";
import {MapImageDTOModel} from "./models/MapImageDTO-model";
import {MapImageResponseModel} from "./models/MapImageResponse-model";
import {MapImageWithRoomsDTOModel} from "./models/MapImageWithRoomsDTO-model";

@Injectable({
  providedIn: 'root'
})
export class MapImageService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  postSaveMapImage(fileUpload:File, mapImageRequest: MapImageResponseModel):Observable<MapImageDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    const jsonData = new Blob([JSON.stringify(mapImageRequest)], {type: "application/json",});

    formData.append("data", jsonData);

    return this.httpClient.post<MapImageDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage`, formData);
  }

  getMapImage(mapId: number) :Observable<MapImageDTOModel>{
    return this.httpClient.get<MapImageDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage?id=${mapId}`)
  }

  getMapImageWithRooms(mapId: number) :Observable<MapImageWithRoomsDTOModel>{
    return this.httpClient.get<MapImageWithRoomsDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage/rooms?id=${mapId}`)
  }
  getSimpleListMapImage():Observable<Array<MapImageResponseModel>>{
    return this.httpClient.get<Array<MapImageResponseModel>>(`${this.config.apiEndpoint}api/v1/mapImage/simple-list`)
  }

  getListMapImage():Observable<Array<MapImageDTOModel>>{
    return this.httpClient.get<Array<MapImageDTOModel>>(`${this.config.apiEndpoint}api/v1/mapImage/list`)
  }

  putMapImage(fileUpload:File, mapImageDTOModel: MapImageDTOModel):Observable<MapImageDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    const jsonData = new Blob([JSON.stringify(mapImageDTOModel)], {type: "application/json",});

    formData.append("data", jsonData);
    return this.httpClient.put<MapImageDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage`, formData);
  }
  deleteFile(fileId: number){
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/mapImage?id=${fileId}`)
  }

}
