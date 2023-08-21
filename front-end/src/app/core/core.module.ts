import {APP_INITIALIZER, ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthConfig, OAuthModule, OAuthModuleConfig, OAuthStorage} from "angular-oauth2-oidc";
import {authConfig} from "./config/auth.config";
import {authModuleConfig} from "./config/auth-module-config";
import {authAppInitializerFactory} from "./config/auth-app-initalizer.factory";
import {AuthService} from "./service/auth.service";
import {HttpClientModule} from "@angular/common/http";

export function storageFactory(): OAuthStorage {
  return localStorage;
}

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    OAuthModule.forRoot()
  ],
  providers:[

  ]
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
