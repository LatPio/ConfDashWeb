import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminComponent} from "./admin.component";


const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {path: 'abstracts',
        loadChildren: () => import('./abstracts-admin/abstracts-admin.module')
          .then(value => value.AbstractsAdminModule)},
      {path: 'customer',
      loadChildren: ()=> import('./customer-admin/customer-admin.module')
        .then(value => value.CustomerAdminModule)},
      {path: 'event',
      loadChildren: ()=> import('./event-admin/event-admin.module')
        .then(value => value.EventAdminModule)},
      {path: 'localization',
      loadChildren:()=> import('./localization-admin/localization-admin.module')
        .then(value => value.LocalizationAdminModule)},
      {path: 'site',
      loadChildren:()=> import('./site-management-admin/site-management-admin.module')
        .then(value => value.SiteManagementAdminModule)},
      {path: 'basket',
        loadChildren:()=> import('./basket-admin/basket-admin.module')
          .then(value => value.BasketAdminModule)},

    ]
  },
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
