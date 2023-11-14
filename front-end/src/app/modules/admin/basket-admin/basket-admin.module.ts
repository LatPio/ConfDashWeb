import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BasketAdminRoutingModule } from './basket-admin-routing.module';
import {BasketAdminListComponent} from "./basket-admin-list/basket-admin-list.component";
import {BasketAdminAddComponent} from "./basket-admin-add/basket-admin-add.component";
import {AbstractsAdminModule} from "../abstracts-admin/abstracts-admin.module";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {EventAdminModule} from "../event-admin/event-admin.module";
import {CustomerAdminModule} from "../customer-admin/customer-admin.module";


@NgModule({
  declarations: [
    BasketAdminListComponent,
    BasketAdminAddComponent,
  ],
  imports: [
    CommonModule,
    BasketAdminRoutingModule,
    AbstractsAdminModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    SharedModule,
    ReactiveFormsModule,
    EventAdminModule,
    CustomerAdminModule,

  ]
})
export class BasketAdminModule { }
