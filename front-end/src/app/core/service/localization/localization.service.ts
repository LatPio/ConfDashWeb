import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {LocalizationDTOModel} from "./models/LocalizationDTOModel";
import {Observable} from "rxjs";
import {StatsLocalizationResponseModel} from "./models/StatsLoclizationResponse-model";

@Injectable({
  providedIn: 'root'
})
export class LocalizationService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  postLocalization(localizationDTO: LocalizationDTOModel):Observable<LocalizationDTOModel>{
    return  this.httpClient.post<LocalizationDTOModel>(`${this.config.apiEndpoint}api/v1/localization`, localizationDTO,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getLocalization(localizationId: number):Observable<LocalizationDTOModel>{
    return  this.httpClient.get<LocalizationDTOModel>(`${this.config.apiEndpoint}api/v1/localization?id=${localizationId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListLocalization():Observable<Array<LocalizationDTOModel>>{
    return  this.httpClient.get<Array<LocalizationDTOModel>>(`${this.config.apiEndpoint}api/v1/localization/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getLocalizationStats():Observable<StatsLocalizationResponseModel> {
    return this.httpClient.get<StatsLocalizationResponseModel>(`${this.config.apiEndpoint}api/v1/localization/stats`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putLocalization(localizationDTO: LocalizationDTOModel):Observable<LocalizationDTOModel>{
    return  this.httpClient.put<LocalizationDTOModel>(`${this.config.apiEndpoint}api/v1/localization`, localizationDTO,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteLocalization(localizationId: number):Observable<LocalizationDTOModel>{
    return  this.httpClient.delete<LocalizationDTOModel>(`${this.config.apiEndpoint}api/v1/localization?id=${localizationId} `,
      {headers:this.httpHeaders, responseType: "json"})
  }
}
