import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import {CoreModule} from "../../core/core.module";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatRadioModule} from "@angular/material/radio";
import {MatButtonModule} from "@angular/material/button";
import {ReactiveFormsModule} from "@angular/forms";
import { SidenavAdminComponent } from './sidenav-admin/sidenav-admin.component';
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {RouterLink} from "@angular/router";
import {MatToolbarModule} from "@angular/material/toolbar";
import { SiteManagementAdminComponent } from './site-management-admin/site-management-admin.component';
import { LocalizationAdminComponent } from './localization-admin/localization-admin.component';
import { EventAdminComponent } from './event-admin/event-admin.component';
import { CustomerAdminComponent } from './customer-admin/customer-admin.component';
import { AbstractsAdminComponent } from './abstracts-admin/abstracts-admin.component';


@NgModule({
  declarations: [
    AdminComponent,
    SidenavAdminComponent,
    SiteManagementAdminComponent,
    LocalizationAdminComponent,
    EventAdminComponent,
    CustomerAdminComponent,
    AbstractsAdminComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatSidenavModule,
    MatRadioModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatListModule,
    RouterLink,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    RouterLink,
    CoreModule.forRoot(),



  ]
})
export class AdminModule { }
