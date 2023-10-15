import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCustomerRoutingModule } from './user-customer-routing.module';
import { UserCustomerComponent } from './user-customer.component';
import { UserCustomerProfileComponent } from './user-customer-profile/user-customer-profile.component';
import { UserCustomerProfileEditComponent } from './user-customer-profile-edit/user-customer-profile-edit.component';
import { UserCustomerListComponent } from './user-customer-list/user-customer-list.component';


@NgModule({
  declarations: [
    UserCustomerComponent,
    UserCustomerProfileComponent,
    UserCustomerProfileEditComponent,
    UserCustomerListComponent
  ],
  imports: [
    CommonModule,
    UserCustomerRoutingModule
  ]
})
export class UserCustomerModule { }
