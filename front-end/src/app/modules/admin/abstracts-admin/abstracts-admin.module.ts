import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AbstractsAdminRoutingModule } from './abstracts-admin-routing.module';
import { AbstractAdminListComponent } from './abstract-admin-list/abstract-admin-list.component';
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatFormFieldModule} from "@angular/material/form-field";
import { AbstractAdminAddComponent } from './abstract-admin-add/abstract-admin-add.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { AbstractAdminGetComponent } from './abstract-admin-get/abstract-admin-get.component';
import { AbstractAdminUpdateComponent } from './abstract-admin-update/abstract-admin-update.component';
import {MatGridListModule} from "@angular/material/grid-list";


@NgModule({
  declarations: [
    AbstractAdminListComponent,
    AbstractAdminAddComponent,
    AbstractAdminGetComponent,
    AbstractAdminUpdateComponent

  ],
  imports: [
    CommonModule,
    AbstractsAdminRoutingModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule

  ]
})
export class AbstractsAdminModule { }
