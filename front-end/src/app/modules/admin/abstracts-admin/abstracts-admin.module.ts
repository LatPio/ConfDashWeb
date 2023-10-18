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
import {MatDialogModule} from '@angular/material/dialog';
import { AbstractAdminAcceptedComponent } from './abstract-admin-accepted/abstract-admin-accepted.component';
import { AbstractAdminToProcessComponent } from './abstracts-admin-to-process/abstract-admin-to-process.component';
import { NgxSummernoteModule } from 'ngx-summernote';
import {MatRadioModule} from "@angular/material/radio";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {SharedModule} from "../../shared/shared.module";
import { AbstractAnalyticalDataComponent } from './abstract-analytical-data/abstract-analytical-data.component';
import { AbstractToProcessComponent } from './abstract-to-process/abstract-to-process.component';
import { AbstractAttachmentFileAddComponent } from './abstract-attachment-file-add/abstract-attachment-file-add.component';
import {MatSelectModule} from "@angular/material/select";
import { AbstractAttachmentFileAddButtonComponent } from './abstract-attachment-file-add-button/abstract-attachment-file-add-button.component';
import { AbstractStatisticComponent } from './abstract-statistic/abstract-statistic.component';
import {MatCardModule} from "@angular/material/card";
import {CustomerAdminModule} from "../customer-admin/customer-admin.module";
import { AbstractAdminAcceptedListComponent } from './abstract-admin-accepted-list/abstract-admin-accepted-list.component';

@NgModule({
  declarations: [
    AbstractAdminListComponent,
    AbstractAdminAddComponent,
    AbstractAdminGetComponent,
    AbstractAdminUpdateComponent,
    AbstractAdminAcceptedComponent,
    AbstractAdminToProcessComponent,
    AbstractAnalyticalDataComponent,
    AbstractToProcessComponent,
    AbstractAttachmentFileAddComponent,
    AbstractAttachmentFileAddButtonComponent,
    AbstractStatisticComponent,
    AbstractAdminAcceptedListComponent

  ],
    exports: [
        AbstractAdminAcceptedListComponent,
        AbstractAttachmentFileAddButtonComponent
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
    MatGridListModule,
    MatDialogModule,
    NgxSummernoteModule,
    MatRadioModule,
    MatCheckboxModule,
    SharedModule,
    MatSelectModule,
    MatCardModule,
    CustomerAdminModule


  ]
})
export class AbstractsAdminModule { }
