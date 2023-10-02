import {Inject, Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {BookingRequestModel} from "./models/BookingRequest-model";
import {BookingDTOModel} from "./models/BookingDTO-model";

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }

  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  postBooking(bookingRequest: BookingRequestModel):Observable<BookingDTOModel>{
    return  this.httpClient.post<BookingDTOModel>(`${this.config.apiEndpoint}api/v1/booking`, bookingRequest,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getBooking(bookingId: number):Observable<BookingDTOModel>{
    return  this.httpClient.get<BookingDTOModel>(`${this.config.apiEndpoint}api/v1/booking?id=${bookingId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListBooking():Observable<Array<BookingDTOModel>>{
    return  this.httpClient.get<Array<BookingDTOModel>>(`${this.config.apiEndpoint}api/v1/booking/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putBooking(bookingDTO: BookingDTOModel):Observable<BookingDTOModel>{
    return  this.httpClient.post<BookingDTOModel>(`${this.config.apiEndpoint}api/v1/booking`, bookingDTO,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteLocalization(bookingId: number){
    return  this.httpClient.delete(`${this.config.apiEndpoint}api/v1/booking?id=${bookingId} `,
      {headers:this.httpHeaders, responseType: "json"})
  }
}
