import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {AttachmentFileAdminRequestModel} from "../abstracts/models/AttachmentFileAdminRequest-model";
import {Observable} from "rxjs";
import {AttachmentFileDTOModel} from "../abstracts/models/AttachmentFileDTO-model";
import {MapImageDTOModel} from "./models/MapImageDTO-model";
import {AttachmentFileResponseModel} from "../abstracts/models/AttachmentFileResponse-model";

@Injectable({
  providedIn: 'root'
})
export class MapImageService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  postSaveMapImage(fileUpload:File):Observable<MapImageDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    return this.httpClient.post<MapImageDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage`, formData);
  }

  getMapImage(fileId: number) :Observable<MapImageDTOModel>{
    return this.httpClient.get<MapImageDTOModel>(`${this.config.apiEndpoint}api/v1/mapImage?id=${fileId}`)
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
