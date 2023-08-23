import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminComponent} from "./admin.component";
import {AbstractsAdminComponent} from "./abstracts-admin/abstracts-admin.component";
import {CustomerAdminComponent} from "./customer-admin/customer-admin.component";
import {EventAdminComponent} from "./event-admin/event-admin.component";
import {LocalizationAdminComponent} from "./localization-admin/localization-admin.component";
import {SiteManagementAdminComponent} from "./site-management-admin/site-management-admin.component";

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {path: 'abstracts',
        loadChildren: () => import('./abstracts-admin/abstracts-admin.module')
          .then(value => value.AbstractsAdminModule)},
      {path: 'customer', component: CustomerAdminComponent},
      {path: 'event', component: EventAdminComponent},
      {path: 'localization', component: LocalizationAdminComponent},
      {path: 'site', component: SiteManagementAdminComponent},

    ]
  },
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
