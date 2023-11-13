import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BasketAdminComponent} from "./basket-admin.component";
import {BasketAdminListComponent} from "./basket-admin-list/basket-admin-list.component";
import {BasketAdminAddComponent} from "./basket-admin-add/basket-admin-add.component";

const routes: Routes = [
  {path:'',
  component: BasketAdminComponent,
  children:[
    {path: 'list', component: BasketAdminListComponent},
    {path: 'add', component: BasketAdminAddComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BasketAdminRoutingModule { }
