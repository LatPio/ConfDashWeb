import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AbstractsAdminComponent} from "./abstracts-admin.component";
import {AbstractAdminListComponent} from "./abstract-admin-list/abstract-admin-list.component";

const routes: Routes = [
  { path:'',
    component: AbstractsAdminComponent,
    children:[
      {path: 'list', component: AbstractAdminListComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AbstractsAdminRoutingModule { }
