import {APP_INITIALIZER, ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthConfig, OAuthModule, OAuthModuleConfig, OAuthStorage} from "angular-oauth2-oidc";
import {authConfig} from "./authentication/auth.config";
import {authModuleConfig} from "./authentication/auth-module-config";
import {authAppInitializerFactory} from "./authentication/auth-app-initalizer.factory";
import {AuthService} from "./authentication/auth.service";
import {HttpClientModule} from "@angular/common/http";
import { NavbarComponent } from './naviagtion/navbar/navbar.component';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";

export function storageFactory(): OAuthStorage {
  return localStorage;
}

@NgModule({
  declarations: [
    NavbarComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    MatButtonModule,
    MatIconModule,
    MatToolbarModule
  ],
  exports: [
    NavbarComponent
  ],
  providers: []
})
export class  CoreModule {
  static forRoot(): ModuleWithProviders<CoreModule> {
    return {
      ngModule : CoreModule,
      providers: [
        // { provide: APP_INITIALIZER, useFactory: authAppInitializerFactory, deps: [AuthService], multi: true },
        { provide: AuthConfig, useValue: authConfig },
        { provide: OAuthModuleConfig, useValue: authModuleConfig },
        { provide: OAuthStorage, useFactory: storageFactory },
      ]
    }
  }
}
