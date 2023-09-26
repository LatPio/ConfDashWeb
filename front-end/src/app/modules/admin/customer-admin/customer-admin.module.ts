import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerAdminRoutingModule } from './customer-admin-routing.module';
import { CustomersListComponent } from './customers-list/customers-list.component';
import { CustomersViewComponent } from './customers-view/customers-view.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentUpdateComponent } from './department-update/department-update.component';
import { DepartmentAddComponent } from './department-add/department-add.component';
import { ProfilePhotoAddComponent } from './profile-photo-add/profile-photo-add.component';
import { ProfilePhotoAddButtonComponent } from './profile-photo-add-button/profile-photo-add-button.component';
import { AffiliationCorrectionComponent } from './affiliation-correction/affiliation-correction.component';
import { CustomerCardComponent } from './cutomer-card/customer-card.component';
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


@NgModule({
  declarations: [
    CustomersListComponent,
    CustomersViewComponent,
    CustomerUpdateComponent,
    DepartmentListComponent,
    DepartmentUpdateComponent,
    DepartmentAddComponent,
    ProfilePhotoAddComponent,
    ProfilePhotoAddButtonComponent,
    AffiliationCorrectionComponent,
    CustomerCardComponent,
    CustomerStatsComponent
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
    MatCardModule
  ]
})
export class CustomerAdminModule { }
