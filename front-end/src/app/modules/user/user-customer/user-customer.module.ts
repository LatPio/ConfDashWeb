import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCustomerRoutingModule } from './user-customer-routing.module';
import { UserCustomerComponent } from './user-customer.component';
import { UserCustomerProfileComponent } from './user-customer-profile/user-customer-profile.component';
import { UserCustomerProfileEditComponent } from './user-customer-profile-edit/user-customer-profile-edit.component';
import { UserCustomerListComponent } from './user-customer-list/user-customer-list.component';
import {SharedModule} from "../../shared/shared.module";
import { UserCustomerViewComponent } from './user-customer-view/user-customer-view.component';
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    UserCustomerComponent,
    UserCustomerProfileComponent,
    UserCustomerProfileEditComponent,
    UserCustomerListComponent,
    UserCustomerViewComponent
  ],
  imports: [
    CommonModule,
    UserCustomerRoutingModule,
    SharedModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatInputModule,
    ReactiveFormsModule
  ]
})
export class UserCustomerModule { }
