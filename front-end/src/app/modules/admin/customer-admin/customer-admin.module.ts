import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerAdminRoutingModule } from './customer-admin-routing.module';
import { CustomersListComponent } from './customers-list/customers-list.component';
import { CustomersViewComponent } from './customers-view/customers-view.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { InvoiceDataListComponent } from './invoice-data-list/Invoice-data-list.component';
import { DepartmentUpdateComponent } from './invoice-data-update/department-update.component';
import { InvoiceDataAddComponent } from './invoice-data-add/invoice-data-add.component';
import { ProfilePhotoAddComponent } from './profile-photo-add/profile-photo-add.component';
import { ProfilePhotoAddButtonComponent } from './profile-photo-add-button/profile-photo-add-button.component';
import { AffiliationCorrectionComponent } from './affiliation-correction/affiliation-correction.component';
import { CustomerCardComponent } from '../../shared/cutomer-card/customer-card.component';
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
import { CustomerCardSmallComponent } from '../../shared/customer-card-small/customer-card-small.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    CustomersListComponent,
    CustomersViewComponent,
    CustomerUpdateComponent,
    InvoiceDataListComponent,
    DepartmentUpdateComponent,
    InvoiceDataAddComponent,
    ProfilePhotoAddComponent,
    ProfilePhotoAddButtonComponent,
    AffiliationCorrectionComponent,
    CustomerStatsComponent,
  ],
  exports: [
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
