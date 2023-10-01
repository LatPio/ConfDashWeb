import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SiteManagementAdminComponent} from "./site-management-admin.component";
import {SiteManagementAdminStatsComponent} from "./site-management-admin-stats/site-management-admin-stats.component";

const routes: Routes = [
  {path:'',
  component: SiteManagementAdminComponent,
  children:[
    {path: 'stats', component: SiteManagementAdminStatsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SiteManagementAdminRoutingModule { }
