import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerAdminRoutingModule } from './customer-admin-routing.module';
import { CustomersListComponent } from './customers-list/customers-list.component';
import { CustomersViewComponent } from './customers-view/customers-view.component';
import { InvoiceDataListComponent } from './invoice-data-list/invoice-data-list.component';
import { InvoiceDataAddComponent } from './invoice-data-add/invoice-data-add.component';
import { CustomerStatsComponent } from './customer-stats/customer-stats.component';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../../shared/shared.module";
import {MatCardModule} from "@angular/material/card";
import {ReactiveFormsModule} from "@angular/forms";
import { CustomerAdminListLightComponent } from './customer-admin-list-light/customer-admin-list-light.component';


@NgModule({
  declarations: [
    CustomersListComponent,
    CustomersViewComponent,
    InvoiceDataListComponent,
    InvoiceDataAddComponent,
    CustomerStatsComponent,
    CustomerAdminListLightComponent,
  ],
    exports: [
        CustomerAdminListLightComponent
    ],
  imports: [
    CommonModule,
    CustomerAdminRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    SharedModule,
    MatCardModule,
    ReactiveFormsModule
  ]
})
export class CustomerAdminModule { }
