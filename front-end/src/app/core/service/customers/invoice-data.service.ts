import {Inject, Injectable} from '@angular/core';
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {InvoiceDataDTOModel} from "./models/InvoiceDataDTOModel";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InvoiceDataService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  postInvoice(invoiceDataDTOModel: InvoiceDataDTOModel): Observable<InvoiceDataDTOModel>{
    return this.httpClient.post<InvoiceDataDTOModel>(`${this.config.apiEndpoint}api/v1/invoice`, invoiceDataDTOModel,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInvoice(invoiceDataId: number): Observable<InvoiceDataDTOModel>{
    return this.httpClient.get<InvoiceDataDTOModel>(`${this.config.apiEndpoint}api/v1/invoice?id=${invoiceDataId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInvoiceList(): Observable<Array<InvoiceDataDTOModel>>{
    return this.httpClient.get<Array<InvoiceDataDTOModel>>(`${this.config.apiEndpoint}api/v1/invoice/list`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  putInvoice(invoiceDataDTOModel: InvoiceDataDTOModel): Observable<InvoiceDataDTOModel>{
    return this.httpClient.put<InvoiceDataDTOModel>(`${this.config.apiEndpoint}api/v1/invoice`, invoiceDataDTOModel,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  deleteInvoice(invoiceDataId: number): Observable<InvoiceDataDTOModel>{
    return this.httpClient.delete<InvoiceDataDTOModel>(`${this.config.apiEndpoint}api/v1/invoice?id=${invoiceDataId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }


}
