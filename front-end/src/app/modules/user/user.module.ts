import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import {CoreModule} from "../../core/core.module";
import {MatSidenavModule} from "@angular/material/sidenav";
import { SideNavUserComponent } from './side-nav-user/side-nav-user.component';
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";


@NgModule({
  declarations: [
    UserComponent,
    SideNavUserComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    CoreModule.forRoot(),
    MatSidenavModule,
    MatIconModule,
    MatListModule

  ]
})
export class UserModule { }
