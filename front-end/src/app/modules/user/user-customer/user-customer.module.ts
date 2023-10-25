import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCustomerRoutingModule } from './user-customer-routing.module';
import { UserCustomerComponent } from './user-customer.component';
import { UserCustomerProfileComponent } from './user-customer-profile/user-customer-profile.component';
import { UserCustomerProfileEditComponent } from './user-customer-profile-edit/user-customer-profile-edit.component';
import { UserCustomerListComponent } from './user-customer-list/user-customer-list.component';
import {SharedModule} from "../../shared/shared.module";
import { UserCustomerViewComponent } from './user-customer-view/user-customer-view.component';


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
    SharedModule
  ]
})
export class UserCustomerModule { }
