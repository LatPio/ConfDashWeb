import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserAbstractRoutingModule } from './user-abstract-routing.module';
import { UserAbstractAddComponent } from './user-abstract-add/user-abstract-add.component';
import { UserAbstractEditComponent } from './user-abstract-edit/user-abstract-edit.component';
import { UserAbstractGlobalListComponent } from './user-abstract-global-list/user-abstract-global-list.component';
import { UserAbstractGlobalViewComponent } from './user-abstract-global-view/user-abstract-global-view.component';
import { UserAbstractViewComponent } from './user-abstract-view/user-abstract-view.component';
import { UserAbstractPanelComponent } from './user-abstract-panel/user-abstract-panel.component';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../../shared/shared.module";
import {EventAdminModule} from "../../admin/event-admin/event-admin.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxSummernoteModule} from "ngx-summernote";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";
import {AbstractsAdminModule} from "../../admin/abstracts-admin/abstracts-admin.module";
import {MatCheckboxModule} from "@angular/material/checkbox";


@NgModule({
  declarations: [
    UserAbstractAddComponent,
    UserAbstractEditComponent,
    UserAbstractGlobalListComponent,
    UserAbstractGlobalViewComponent,
    UserAbstractViewComponent,
    UserAbstractPanelComponent
  ],
  imports: [
    CommonModule,
    UserAbstractRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    SharedModule,
    EventAdminModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSummernoteModule,
    MatDividerModule,
    MatCardModule,
    AbstractsAdminModule,
    MatCheckboxModule
  ]
})
export class UserAbstractModule { }
