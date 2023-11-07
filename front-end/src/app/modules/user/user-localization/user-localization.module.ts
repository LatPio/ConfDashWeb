import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserLocalizationRoutingModule } from './user-localization-routing.module';
import { UserLocalizationComponent } from './user-localization.component';
import { UserMapListComponent } from './user-map-list/user-map-list.component';
import { UserLocalizationListComponent } from './user-localization-list/user-localization-list.component';
import { UserLocalziationViewComponent } from './user-localziation-view/user-localziation-view.component';
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";
import { UserMapViewComponent } from './user-map-view/user-map-view.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatSortModule} from "@angular/material/sort";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    UserLocalizationComponent,
    UserMapListComponent,
    UserLocalizationListComponent,
    UserLocalziationViewComponent,
    UserMapViewComponent
  ],
    imports: [
        CommonModule,
        UserLocalizationRoutingModule,
        MatButtonModule,
        MatDividerModule,
        MatCardModule,
        MatFormFieldModule,
        MatPaginatorModule,
        MatInputModule,
        MatTableModule,
        MatIconModule,
        MatSortModule,
        SharedModule
    ]
})
export class UserLocalizationModule { }
