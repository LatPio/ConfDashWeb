import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerAdminComponent} from "./customer-admin.component";
import {CustomersListComponent} from "./customers-list/customers-list.component";
import {CustomerUpdateComponent} from "./customer-update/customer-update.component";
import {CustomersViewComponent} from "./customers-view/customers-view.component";
import {DepartmentListComponent} from "./department-list/department-list.component";
import {DepartmentAddComponent} from "./department-add/department-add.component";
import {AffiliationCorrectionComponent} from "./affiliation-correction/affiliation-correction.component";
import {CustomerStatsComponent} from "./customer-stats/customer-stats.component";

const routes: Routes = [
  {path:'',
    component: CustomerAdminComponent,
    children:[
      {path: 'list', component:CustomersListComponent},
      {path: 'update', component: CustomerUpdateComponent},
      {path: 'view/:customerID', component: CustomersViewComponent},
      {path: 'department-list', component: DepartmentListComponent},
      {path: 'department-add', component: DepartmentAddComponent},
      {path: 'affiliation-correction', component: AffiliationCorrectionComponent},
      {path: 'stats', component: CustomerStatsComponent}

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerAdminRoutingModule { }
