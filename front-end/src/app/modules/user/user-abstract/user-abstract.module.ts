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
import { UserAbstractAttachFileComponent } from './user-abstract-attach-file/user-abstract-attach-file.component';
import { UserAbstractAttachFileButtonComponent } from './user-abstract-attach-file-button/user-abstract-attach-file-button.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatExpansionModule} from "@angular/material/expansion";
import { UserAbstractEditFileComponent } from './user-abstract-edit-file/user-abstract-edit-file.component';


@NgModule({
  declarations: [
    UserAbstractAddComponent,
    UserAbstractEditComponent,
    UserAbstractGlobalListComponent,
    UserAbstractGlobalViewComponent,
    UserAbstractViewComponent,
    UserAbstractPanelComponent,
    UserAbstractAttachFileComponent,
    UserAbstractAttachFileButtonComponent,
    UserAbstractEditFileComponent
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
    MatCheckboxModule,
    MatDialogModule,
    MatOptionModule,
    MatSelectModule,
    MatExpansionModule
  ]
})
export class UserAbstractModule { }
