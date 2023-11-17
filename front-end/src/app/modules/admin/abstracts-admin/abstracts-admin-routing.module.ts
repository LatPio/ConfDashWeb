import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AbstractsAdminComponent} from "./abstracts-admin.component";
import {AbstractAdminListComponent} from "./abstract-admin-list/abstract-admin-list.component";
import {AbstractAdminAddComponent} from "./abstract-admin-add/abstract-admin-add.component";
import {AbstractAdminGetComponent} from "./abstract-admin-get/abstract-admin-get.component";
import {AbstractAdminUpdateComponent} from "./abstract-admin-update/abstract-admin-update.component";
import {AbstractAdminAcceptedComponent} from "./abstract-admin-accepted/abstract-admin-accepted.component";
import {AbstractAdminToProcessComponent} from "./abstracts-admin-to-process/abstract-admin-to-process.component";
import {AbstractStatisticComponent} from "./abstract-statistic/abstract-statistic.component";

const routes: Routes = [
  { path:'',
    component: AbstractsAdminComponent,
    children:[
      {path: 'list', component: AbstractAdminListComponent},
      {path: 'add', component: AbstractAdminAddComponent},
      {path: 'get/:abstractID', component: AbstractAdminGetComponent},
      {path: 'update/:abstractID', component: AbstractAdminUpdateComponent},
      {path: 'accepted', component: AbstractAdminAcceptedComponent},
      {path: 'pending', component: AbstractAdminToProcessComponent},
      {path: 'stats', component:AbstractStatisticComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AbstractsAdminRoutingModule { }
