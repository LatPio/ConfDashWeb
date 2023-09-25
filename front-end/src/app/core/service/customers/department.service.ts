import {Inject, Injectable} from '@angular/core';
import {APP_CONFIG, AppConfig} from "../../config/app-config/app-config.module";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DepartmentDTOModel} from "./models/DepartmentDTO-model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(
    private httpClient: HttpClient,
    @Inject(APP_CONFIG) private config: AppConfig
  ) { }
  httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  postDepartment(departmentDTO: DepartmentDTOModel): Observable<DepartmentDTOModel>{
    return this.httpClient.post<DepartmentDTOModel>(`${this.config.apiEndpoint}api/v1/department`, departmentDTO,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getDepartment(departmentId: number): Observable<DepartmentDTOModel>{
    return this.httpClient.get<DepartmentDTOModel>(`${this.config.apiEndpoint}api/v1/department?id=${departmentId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  getDepartmentList(): Observable<Array<DepartmentDTOModel>>{
    return this.httpClient.get<Array<DepartmentDTOModel>>(`${this.config.apiEndpoint}api/v1/department`,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  putDepartment(departmentDTO: DepartmentDTOModel): Observable<DepartmentDTOModel>{
    return this.httpClient.put<DepartmentDTOModel>(`${this.config.apiEndpoint}api/v1/department`, departmentDTO,
      {headers:this.httpHeaders, responseType: "json"} )
  }

  deleteDepartment(departmentId: number): Observable<DepartmentDTOModel>{
    return this.httpClient.delete<DepartmentDTOModel>(`${this.config.apiEndpoint}api/v1/department?id=${departmentId}`,
      {headers:this.httpHeaders, responseType: "json"} )
  }


}
