import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserCustomerComponent} from "./user-customer.component";
import {UserCustomerListComponent} from "./user-customer-list/user-customer-list.component";
import {UserCustomerProfileComponent} from "./user-customer-profile/user-customer-profile.component";
import {UserCustomerProfileEditComponent} from "./user-customer-profile-edit/user-customer-profile-edit.component";

const routes: Routes = [
  {path: '',
   component: UserCustomerComponent,
  children: [
    {path: 'list', component: UserCustomerListComponent},
    {path: 'profile', component: UserCustomerProfileComponent},
    {path: 'edit', component: UserCustomerProfileEditComponent},

  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserCustomerRoutingModule { }
