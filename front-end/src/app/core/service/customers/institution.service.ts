import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {DepartmentDTOModel} from "./models/DepartmentDTO-model";
import {Observable} from "rxjs";
import {InstitutionDTOModel} from "./models/InstitutionDTO-model";

@Injectable({
  providedIn: 'root'
})
export class InstitutionService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  postInstitution(institutionDTO: InstitutionDTOModel): Observable<InstitutionDTOModel>{
    return this.httpClient.post<InstitutionDTOModel>(`${this.config.apiEndpoint}api/v1/institution`, institutionDTO,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInstitution(institutionId: number): Observable<InstitutionDTOModel>{
    return this.httpClient.get<InstitutionDTOModel>(`${this.config.apiEndpoint}api/v1/institution?id=${institutionId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getInstitutionList(): Observable<Array<InstitutionDTOModel>>{
    return this.httpClient.get<Array<InstitutionDTOModel>>(`${this.config.apiEndpoint}api/v1/institution/list`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  putInstitution(institutionDTO: InstitutionDTOModel): Observable<InstitutionDTOModel>{
    return this.httpClient.put<InstitutionDTOModel>(`${this.config.apiEndpoint}api/v1/institution`, institutionDTO,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  deleteInstitution(institutionId: number): Observable<InstitutionDTOModel>{
    return this.httpClient.delete<InstitutionDTOModel>(`${this.config.apiEndpoint}api/v1/institution?id=${institutionId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }}
