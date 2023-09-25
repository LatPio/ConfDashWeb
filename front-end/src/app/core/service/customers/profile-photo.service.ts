import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {Observable} from "rxjs";
import {ProfilePhotoDTOModel} from "./models/ProfilePhotoDTO-model";

@Injectable({
  providedIn: 'root'
})
export class ProfilePhotoService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  postSaveProfilePhotoAdmin(fileUpload:File, customerId: number):Observable<ProfilePhotoDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);


    return this.httpClient.post<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/admin/profile_photo?id=${customerId}`, formData);
  }

  getProfilePhotoAdmin(fileId: number): Observable<ProfilePhotoDTOModel>{
    return this.httpClient.get<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/admin/profile_photo?id=${fileId}`)
  }

  putUpdateProfilePhotoAdmin(fileUpload:File, customerId: number):Observable<ProfilePhotoDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    return this.httpClient.put<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/admin/profile_photo?id=${customerId}`, formData);
  }

  deletePhotoAdmin(fileId: number){
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/admin/profile_photo?id=${fileId}`)
  }


  //User


  postSaveProfilePhotoUser(fileUpload:File, customerId: number):Observable<ProfilePhotoDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    // const jsonData = new Blob([JSON.stringify(attachesFileAdminRequest)], {type: "application/json",});
    // formData.append("data", jsonData);

    return this.httpClient.post<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/user/profile_photo?id=${customerId}`, formData);
  }

  getProfilePhotoUser(fileId: number): Observable<ProfilePhotoDTOModel>{
    return this.httpClient.get<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/user/profile_photo?id=${fileId}`)
  }

  putUpdateProfilePhotoUser(fileUpload:File, customerId: number):Observable<ProfilePhotoDTOModel>{
    const formData: FormData = new FormData();

    formData.append("file", fileUpload);

    return this.httpClient.put<ProfilePhotoDTOModel>(`${this.config.apiEndpoint}api/v1/user/profile_photo?id=${customerId}`, formData);
  }

  deletePhotoUser(fileId: number){
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/user/profile_photo?id=${fileId}`)
  }

}
