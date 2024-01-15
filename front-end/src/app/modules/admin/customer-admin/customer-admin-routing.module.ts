import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerAdminComponent} from "./customer-admin.component";
import {CustomersListComponent} from "./customers-list/customers-list.component";
import {CustomersViewComponent} from "./customers-view/customers-view.component";
import {InvoiceDataListComponent} from "./invoice-data-list/invoice-data-list.component";
import {InvoiceDataAddComponent} from "./invoice-data-add/invoice-data-add.component";
import {CustomerStatsComponent} from "./customer-stats/customer-stats.component";

const routes: Routes = [
  {path:'',
    component: CustomerAdminComponent,
    children:[
      {path: 'list', component:CustomersListComponent},
      {path: 'view/:customerID', component: CustomersViewComponent},
      {path: 'invoice-list', component: InvoiceDataListComponent},
      {path: 'invoice-add', component: InvoiceDataAddComponent},
      {path: 'stats', component: CustomerStatsComponent}

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerAdminRoutingModule { }
