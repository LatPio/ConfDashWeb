import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCustomerRoutingModule } from './user-customer-routing.module';
import { UserCustomerComponent } from './user-customer.component';
import { UserCustomerProfileComponent } from './user-customer-profile/user-customer-profile.component';
import { UserCustomerProfileEditComponent } from './user-customer-profile-edit/user-customer-profile-edit.component';
import { UserCustomerListComponent } from './user-customer-list/user-customer-list.component';
import {SharedModule} from "../../shared/shared.module";
import { UserCustomerViewComponent } from './user-customer-view/user-customer-view.component';
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import { UserCustomerPhotoAddButtonComponent } from './user-customer-photo-add-button/user-customer-photo-add-button.component';
import { UserCustomerPhotoAddComponent } from './user-customer-photo-add/user-customer-photo-add.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import { UserCustomerLinksEditComponent } from './user-customer-links-edit/user-customer-links-edit.component';
import {EventAdminModule} from "../../admin/event-admin/event-admin.module";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {AbstractsAdminModule} from "../../admin/abstracts-admin/abstracts-admin.module";
import { UserCustomerLinksEditDialogComponent } from './user-customer-links-edit-dialog/user-customer-links-edit-dialog.component';


@NgModule({
  declarations: [
    UserCustomerComponent,
    UserCustomerProfileComponent,
    UserCustomerProfileEditComponent,
    UserCustomerListComponent,
    UserCustomerViewComponent,
    UserCustomerPhotoAddButtonComponent,
    UserCustomerPhotoAddComponent,
    UserCustomerLinksEditComponent,
    UserCustomerLinksEditDialogComponent
  ],
  imports: [
    CommonModule,
    UserCustomerRoutingModule,
    SharedModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatOptionModule,
    MatSelectModule,
    EventAdminModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    AbstractsAdminModule
  ]
})
export class UserCustomerModule { }
