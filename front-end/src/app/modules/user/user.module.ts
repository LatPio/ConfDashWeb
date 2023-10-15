import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import {CoreModule} from "../../core/core.module";
import {MatSidenavModule} from "@angular/material/sidenav";
import { SideNavUserComponent } from './side-nav-user/side-nav-user.component';
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import { UserAbstractComponent } from './user-abstract/user-abstract.component';


@NgModule({
  declarations: [
    UserComponent,
    SideNavUserComponent,
    UserAbstractComponent
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
