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
import { CutomerCardComponent } from './cutomer-card/cutomer-card.component';
import { CustomerStatsComponent } from './customer-stats/customer-stats.component';


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
    CutomerCardComponent,
    CustomerStatsComponent
  ],
  imports: [
    CommonModule,
    CustomerAdminRoutingModule
  ]
})
export class CustomerAdminModule { }
