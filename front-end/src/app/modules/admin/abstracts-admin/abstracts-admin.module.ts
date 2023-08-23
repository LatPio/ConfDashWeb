import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AbstractsAdminRoutingModule } from './abstracts-admin-routing.module';
import { AbstractAdminListComponent } from './abstract-admin-list/abstract-admin-list.component';
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";


@NgModule({
  declarations: [
    AbstractAdminListComponent

  ],
  imports: [
    CommonModule,
    AbstractsAdminRoutingModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
  ]
})
export class AbstractsAdminModule { }
