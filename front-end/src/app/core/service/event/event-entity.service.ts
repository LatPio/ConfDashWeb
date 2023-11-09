import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {EventEntityDTOModel} from "./models/EventEntityDTO-model";
import {Observable} from "rxjs";
import {AbstractDTOModel} from "../abstracts/models/AbstractDTO-model";
import {EventStatisticModel} from "./models/EventStatistic-model";

@Injectable({
  providedIn: 'root'
})
export class EventEntityService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  postEvent(event: EventEntityDTOModel):Observable<EventEntityDTOModel>{
    return this.httpClient.post<EventEntityDTOModel>(`${this.config.apiEndpoint}api/v1/event`, event,
      {headers:this.httpHeaders, responseType: "json"})
  }
  getEvent(eventID: number):Observable<EventEntityDTOModel>{
    return this.httpClient.get<EventEntityDTOModel>(`${this.config.apiEndpoint}api/v1/event?id=${eventID}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListEvent():Observable<Array<EventEntityDTOModel>>{
    return this.httpClient.get<Array<EventEntityDTOModel>>(`${this.config.apiEndpoint}api/v1/event/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListEventByLocation(locationId: number):Observable<Array<EventEntityDTOModel>>{
    return this.httpClient.get<Array<EventEntityDTOModel>>(`${this.config.apiEndpoint}api/v1/event/list/room?id=${locationId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putEvent(event: EventEntityDTOModel):Observable<EventEntityDTOModel>{
    return this.httpClient.put<EventEntityDTOModel>(`${this.config.apiEndpoint}api/v1/event`, event,
      {headers:this.httpHeaders, responseType: "json"})
  }
  deleteEvent(eventID: number):Observable<EventEntityDTOModel>{
    return this.httpClient.delete<EventEntityDTOModel>(`${this.config.apiEndpoint}api/v1/event?id=${eventID}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getStatistic():Observable<EventStatisticModel> {
    return this.httpClient.get<EventStatisticModel>(`${this.config.apiEndpoint}api/v1/event/stats`,
      {headers:this.httpHeaders, responseType: "json"})
  }

}
