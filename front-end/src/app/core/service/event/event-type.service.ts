import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {Observable} from "rxjs";
import {EventTypeDTOModel} from "./models/EventTypeDTO-model";

@Injectable({
  providedIn: 'root'
})
export class EventTypeService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})



  postEventType(eventType: EventTypeDTOModel):Observable<EventTypeDTOModel>{
    return this.httpClient.post<EventTypeDTOModel>(`${this.config.apiEndpoint}api/v1/event-type`, eventType,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListEventType():Observable<Array<EventTypeDTOModel>>{
    return this.httpClient.get<Array<EventTypeDTOModel>>(`${this.config.apiEndpoint}api/v1/event-type/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getEventType(eventTypeID: number):Observable<EventTypeDTOModel>{
    return this.httpClient.get<EventTypeDTOModel>(`${this.config.apiEndpoint}api/v1/event-type?id=${eventTypeID}`,
      {headers:this.httpHeaders, responseType: "json"})
  }
  putEventType(eventType: EventTypeDTOModel):Observable<EventTypeDTOModel>{
    return this.httpClient.put<EventTypeDTOModel>(`${this.config.apiEndpoint}api/v1/event-type`, eventType,
      {headers:this.httpHeaders, responseType: "json"})
  }
  deleteEventType(eventTypeID: number):Observable<EventTypeDTOModel>{
    return this.httpClient.delete<EventTypeDTOModel>(`${this.config.apiEndpoint}api/v1/event-type?id=${eventTypeID}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

}
