import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserComponent} from "./user.component";

const routes: Routes = [
  { path: '',
    component: UserComponent,
    children: [
      { path: 'abstracts',
        loadChildren: () => import('./user-abstract/user-abstract.module')
          .then(value => value.UserAbstractModule)},
      {path:'participants',
      loadChildren: () => import('./user-customer/user-customer.module')
        .then(value => value.UserCustomerModule)},
      {path:'booked',
      loadChildren:() => import('./user-booking/user-booking.module')
        .then(value => value.UserBookingModule)},
      {path:'localization',
      loadChildren:() => import('./user-localization/user-localization.module')
        .then(value => value.UserLocalizationModule)}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
