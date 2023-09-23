import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './core/page-not-found/page-not-found.component';
import {HttpClientModule} from "@angular/common/http";
import {CoreModule} from "./core/core.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppConfigModule} from "./core/config/app-config/app-config.module";
import {MatDialogModule} from '@angular/material/dialog';
import { MatIconModule } from "@angular/material/icon";



@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule.forRoot(),
    AppConfigModule,
    MatDialogModule,
    MatIconModule,

    // OAuthModule.forRoot(
    //   {
    //     resourceServer: {
    //       //Interceptor settings this provides bearer token to every call for this url
    //       allowedUrls: ['http://localhost:8080/'],
    //       sendAccessToken: true
    //     }
    //   }
    // ),
        BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
