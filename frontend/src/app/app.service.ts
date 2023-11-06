import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {OAuthService} from "angular-oauth2-oidc";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private  httpClient: HttpClient,
              private oAuthService: OAuthService) { }

  getData(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json')
      .append('Authorization', 'Bearer '+this.oAuthService.getAccessToken())

    return this.httpClient.get('/api/v1/admin/abstracts/list',
      {headers, responseType: "json"})
  }

  getData2(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json')
      .append('Authorization', 'Bearer '+this.oAuthService.getAccessToken())

    return this.httpClient.get('http://localhost:8080/api/v1/admin/abstracts/list',
      {headers, responseType: "json"})
  }
}
