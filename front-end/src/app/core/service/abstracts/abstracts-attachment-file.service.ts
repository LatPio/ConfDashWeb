import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {Observable} from "rxjs";
import {AttachmentFileDTOModel} from "./models/AttachmentFileDTO-model";
import {AttachmentFileAdminRequestModel} from "./models/AttachmentFileAdminRequest-model";
import {AttachmentFileResponseModel} from "./models/AttachmentFileResponse-model";

@Injectable({
  providedIn: 'root'
})
export class AbstractsAttachmentFileService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  postSaveFile(fileUpload:File, attachesFileAdminRequest: AttachmentFileAdminRequestModel):Observable<AttachmentFileDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    const jsonData = new Blob([JSON.stringify(attachesFileAdminRequest)], {type: "application/json",});

    formData.append("data", jsonData);
    return this.httpClient.post<AttachmentFileDTOModel>(`${this.config.apiEndpoint}api/v1/admin/attachment_file`, formData);
  }


  getFile(fileId: number) :Observable<AttachmentFileDTOModel>{
    return this.httpClient.get<AttachmentFileDTOModel>(`${this.config.apiEndpoint}api/v1/admin/attachment_file?id=${fileId}`)
  }

  getDownloadFile(fileId: number): Observable<Blob>{
    return this.httpClient.get<Blob>(`${this.config.apiEndpoint}api/v1/admin/attachment_file/file/${fileId}`)
  }

  getListOfAllFiles():Observable<Array<AttachmentFileResponseModel>>{
    return this.httpClient.get<Array<AttachmentFileResponseModel>>(`${this.config.apiEndpoint}api/v1/admin/attachment_file/list`)
  }

  deleteFile(fileId: number){
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/admin/attachment_file?id=${fileId}`)
  }



}
