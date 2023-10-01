import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {Observable} from "rxjs";
import {CustomerAdminDTOModel} from "./models/CustomerAdminDTO-model";
import {CustomerAdminModule} from "../../../modules/admin/customer-admin/customer-admin.module";
import {CustomerRegistrationRequestModel} from "./models/CustomerRegistrationRequest-model";
import {CustomerCardDTOModel} from "./models/CustomerCardDTO-model";
import {CustomerStatsResponseModel} from "./models/CustomerStatsResponseModel";

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  getCustomerAdmin(customerId: number): Observable<CustomerAdminDTOModel>{
    return this.httpClient.get<CustomerAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/customer?id=${customerId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getCustomersAdminList():Observable<Array<CustomerAdminDTOModel>>{
    return this.httpClient.get<Array<CustomerAdminDTOModel>>(`${this.config.apiEndpoint}api/v1/admin/customer/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putCustomersAdmin(customer: CustomerAdminDTOModel):Observable<CustomerAdminDTOModel>{
    return this.httpClient.put<CustomerAdminDTOModel>(`${this.config.apiEndpoint}api/v1/admin/customer`, customer,
      {headers:this.httpHeaders, responseType: "json"})
  }

  deleteCustomersAdmin(customer: CustomerAdminDTOModel):Observable<CustomerAdminModule>{
    return this.httpClient.put<CustomerAdminModule>(`${this.config.apiEndpoint}api/v1/admin/customer?id:${customer.id}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getCustomersStatsAdmin():Observable<CustomerStatsResponseModel>{
    return this.httpClient.get<CustomerStatsResponseModel>(`${this.config.apiEndpoint}api/v1/admin/customer/stats`,
      {headers:this.httpHeaders, responseType: "json"})
  }



//   User

  postCustomerUser(customer: CustomerRegistrationRequestModel): Observable<any>{
    return this.httpClient.post(`${this.config.apiEndpoint}api/v1/user/customer/new`, customer,
      {headers:this.httpHeaders, responseType: "text"})
  }
  getCustomersUser(customerId: number):Observable<CustomerAdminDTOModel>{
    return this.httpClient.get<CustomerAdminDTOModel>(`${this.config.apiEndpoint}api/v1/user/customer?id=${customerId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getCustomersUserCard(customerId: number):Observable<CustomerCardDTOModel>{
    return this.httpClient.get<CustomerCardDTOModel>(`${this.config.apiEndpoint}api/v1/user/customer/card?id=${customerId}`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  getCustomersUSerCardList():Observable<Array<CustomerCardDTOModel>>{
    return this.httpClient.get<Array<CustomerCardDTOModel>>(`${this.config.apiEndpoint}api/v1/user/customer/list`,
      {headers:this.httpHeaders, responseType: "json"})
  }

  putCustomersUSer(customer: CustomerAdminDTOModel):Observable<CustomerAdminDTOModel>{
    return this.httpClient.put<CustomerAdminDTOModel>(`${this.config.apiEndpoint}api/v1/user/customer`, customer,
      {headers:this.httpHeaders, responseType: "json"})
  }
}
