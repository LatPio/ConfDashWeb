import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {BasketDTOModel} from "./models/basketDTO-model";
import {Observable} from "rxjs";
import {EventEntityDTOModel} from "../event/models/EventEntityDTO-model";
import {BasketRequestModel} from "./models/basketRequest-model";

@Injectable({
  providedIn: 'root'
})
export class BasketService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  // Admin
  postAdminBasket(basketDTO: BasketDTOModel): Observable<any>{
    return this.httpClient.post(`${this.config.apiEndpoint}api/v1/admin/basket`, basketDTO,
      {headers:this.httpHeaders, responseType: "json"})
  }

  postAdminManyBasketItems(basketsRequest: BasketRequestModel): Observable<any>{
    return this.httpClient.post(`${this.config.apiEndpoint}api/v1/admin/basket/many`, basketsRequest,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getAdminBasket(id: number, authId: string): Observable<BasketDTOModel>{
    return this.httpClient.get<BasketDTOModel>(`${this.config.apiEndpoint}api/v1/admin/basket?id=${id}&authId=${authId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListForUserAdminBasket(authId: string): Observable<Array<BasketDTOModel>>{
    return this.httpClient.get<Array<BasketDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/basket/list?authId=${authId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListAllItemsAdminBasket(): Observable<Array<BasketDTOModel>>{
    return this.httpClient.get<Array<BasketDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/basket/list-all`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteAdminBasket(item: BasketDTOModel): Observable<any>{
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/admin/basket?id=${item.id}&authId=${item.authId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  //User

  postUserBasket(eventEntityDTO: EventEntityDTOModel): Observable<any>{
    return this.httpClient.post(`${this.config.apiEndpoint}api/v1/user/basket`, eventEntityDTO,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getUserBasket(id: number): Observable<BasketDTOModel>{
    return this.httpClient.get<BasketDTOModel>(`${this.config.apiEndpoint}api/v1/user/basket?id=${id}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getListForUserBasket(): Observable<Array<BasketDTOModel>>{
    return this.httpClient.get<Array<BasketDTOModel>>(`${this.config.apiEndpoint}api/v1/user/basket/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteUserBasket(basketDTO: BasketDTOModel): Observable<any>{
    return this.httpClient.delete(`${this.config.apiEndpoint}api/v1/user/basket?id=${basketDTO.id}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

}
