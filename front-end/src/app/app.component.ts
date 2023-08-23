import { Component } from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {authConfig} from "./core/authentication/auth.config";
import {AuthService} from "./core/authentication/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front-end';


  constructor(private authService: AuthService) {
    // this.configure();

    this.authService.runInitialLoginSequence();
  }
  //
  // // private configure(){
  // //   this.oauthService.configure(authConfig);
  // //   this.oauthService.loadDiscoveryDocumentAndLogin().then();
  //   // this.oauthService.setupAutomaticSilentRefresh();
  // // }
  //
  // login(){
  //   this.authService.login("admin");
  //   // this.redirectToUSerOrAdmin();
  // }
  //
  // logout(){
  //   this.authService.logout();
  //   // this.oauthService.revokeTokenAndLogout().then();
  //
  // }
}
