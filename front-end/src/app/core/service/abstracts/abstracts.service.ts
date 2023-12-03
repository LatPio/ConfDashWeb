import {Inject, Injectable} from '@angular/core';
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AbstractDTOModel} from "./models/AbstractDTO-model";
import {Observable} from "rxjs";
import {AbstractOutResponseModel} from "./models/AbstractOutResponse-model";
import {AbstractBlockDTOModel} from "./models/abstractBlockDTO-model";
import {StatsResponseModel} from "./models/statsResponse-model";
import {AbstractLightDTOModel} from "./models/AbstractLightDTO-model";

@Injectable({
  providedIn: 'root'
})
export class AbstractsService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  postAbstractAdmin(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.post<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts`, abstract,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAbstractAdmin(abstractId: number): Observable<AbstractDTOModel>{
    return this.httpClient.get<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts?id=${abstractId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAbstractSimpleAdmin(abstractId: number): Observable<AbstractOutResponseModel>{
    return this.httpClient.get<AbstractOutResponseModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts/simple?id=${abstractId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAbstractListAdmin(): Observable<Array<AbstractDTOModel>>{

    return this.httpClient.get<Array<AbstractDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/abstracts/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  getAbstractLightListAdmin(): Observable<Array<AbstractLightDTOModel>>{

    return this.httpClient.get<Array<AbstractLightDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/abstracts/approved/light-list?accepted=${true}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  getAbstractListAdminAccepted(accepted: boolean): Observable<Array<AbstractDTOModel>>{

    return this.httpClient.get<Array<AbstractDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/abstracts/approved?accepted=${accepted}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  putAbstractBlockAdmin(abstract: AbstractBlockDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.put<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts/block`, abstract,
      {headers:this.httpHeaders, responseType: "json"})
  }
  putAbstractAdmin(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.put<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts`, abstract,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteAbstractAdmin(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.delete<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts?id=${abstract.id}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  getStatsAdmin(): Observable<StatsResponseModel>{
    return this.httpClient.get<StatsResponseModel>(`${this.config.apiEndpoint}api/v1/admin/abstracts/stats`,
      {headers:this.httpHeaders, responseType: "json"})
  }







  postAbstractUser(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.post<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/user/abstracts`, abstract,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAbstractUser(abstractId: number): Observable<AbstractDTOModel>{
    return this.httpClient.get<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/user/abstracts?id=${abstractId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  getAbstractAccepted(abstractId: number): Observable<AbstractDTOModel>{
    return this.httpClient.get<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/user/abstracts/accepted?id=${abstractId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAbstractListUser(): Observable<Array<AbstractDTOModel>>{
    return this.httpClient.get<Array<AbstractDTOModel>>(`${this.config.apiEndpoint}api/v1/user/abstracts/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }


  getAbstractAcceptedListUser(): Observable<Array<AbstractDTOModel>>{
    return this.httpClient.get<Array<AbstractDTOModel>>(`${this.config.apiEndpoint}api/v1/user/abstracts/listAccepted`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putAbstractUser(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.put<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/user/abstracts`, abstract,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteAbstractUser(abstract: AbstractDTOModel): Observable<AbstractDTOModel>{
    return this.httpClient.delete<AbstractDTOModel>(`${this.config.apiEndpoint}api/v1/user/abstracts?id=${abstract.id}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
}
