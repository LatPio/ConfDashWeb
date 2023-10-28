import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {InvoiceDataDTOModel} from "./models/InvoiceDataDTOModel";
import {Observable} from "rxjs";
import {InformationLinksUserDTOModel} from "./models/InformationLinksUserDTOModel";
import {InformationLinksRequestModel} from "./models/InformationLinksRequest-model";
import {InformationLinksAdminDTOModel} from "./models/InformationLinksAdminDTOModel";
import {InformationLinksAdminRequestModel} from "./models/InformationLinksAdminRequest-model";

@Injectable({
  providedIn: 'root'
})
export class InformationLinksService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  postInformationLinkAdmin(informationLink: InformationLinksAdminRequestModel): Observable<InformationLinksAdminDTOModel>{
    return this.httpClient.post<InformationLinksAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/info_links`, informationLink,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInformationLinkAdmin(informationLinkId: number): Observable<InformationLinksAdminDTOModel>{
    return this.httpClient.get<InformationLinksAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/info_links?id=${informationLinkId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInformationLinkListAdmin(): Observable<Array<InformationLinksAdminDTOModel>>{
    return this.httpClient.get<Array<InformationLinksAdminDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/info_links`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  putInformationLinkAdmin(informationLink: InformationLinksAdminDTOModel): Observable<InformationLinksAdminDTOModel>{
    return this.httpClient.put<InformationLinksAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/info_links`, informationLink,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  deleteInformationLinkAdmin(informationLinkId: number): Observable<InformationLinksAdminDTOModel>{
    return this.httpClient.delete<InformationLinksAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/info_links?id=${informationLinkId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

//   User

  postInformationLinkUser(informationLink: InformationLinksRequestModel): Observable<InformationLinksUserDTOModel>{
    return this.httpClient.post<InformationLinksUserDTOModel>(`${this.config.apiEndpoint}api/v1/user/info_links`, informationLink,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInformationLinkUser(informationLinkId: number): Observable<InformationLinksUserDTOModel>{
    return this.httpClient.get<InformationLinksUserDTOModel>(`${this.config.apiEndpoint}api/v1/user/info_links?id=${informationLinkId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInformationLinkListUser(): Observable<Array<InformationLinksUserDTOModel>>{
    return this.httpClient.get<Array<InformationLinksUserDTOModel>>(`${this.config.apiEndpoint}api/v1/user/info_links`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  putInformationLinkUser(informationLink: InformationLinksUserDTOModel): Observable<InformationLinksUserDTOModel>{
    return this.httpClient.put<InformationLinksUserDTOModel>(`${this.config.apiEndpoint}api/v1/user/info_links`, informationLink,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  deleteInformationLinkUser(informationLinkId: number): Observable<InformationLinksUserDTOModel>{
    return this.httpClient.delete<InformationLinksUserDTOModel>(`${this.config.apiEndpoint}api/v1/user/info_links?id=${informationLinkId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

}
