import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventAdminRoutingModule } from './event-admin-routing.module';
import { EventAdminStatsComponent } from './event-admin-stats/event-admin-stats.component';
import { EventAdminAddComponent } from './event-admin-add/event-admin-add.component';
import { EventAdminTypeAddComponent } from './event-admin-type-add/event-admin-type-add.component';
import { EventAdminTypeUpdateComponent } from './event-admin-type-update/event-admin-type-update.component';
import { EventAdminUpdateComponent } from './event-admin-update/event-admin-update.component';
import { EventAdminListComponent } from './event-admin-list/event-admin-list.component';
import { EventAdminTypeListComponent } from './event-admin-type-list/event-admin-type-list.component';
import { EventAdminViewComponent } from './event-admin-view/event-admin-view.component';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../../shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {NgxMatDatetimePickerModule} from "@angular-material-components/datetime-picker";
import {LocalizationAdminModule} from "../localization-admin/localization-admin.module";
import { EventAdminTypeListLightComponent } from './event-admin-type-list-light/event-admin-type-list-light.component';
import {AbstractsAdminModule} from "../abstracts-admin/abstracts-admin.module";
import { EventSimpleViewCardComponent } from './event-simple-view-card/event-simple-view-card.component';
import {MatCardModule} from "@angular/material/card";
import { EventViewCardComponent } from './event-view-card/event-view-card.component';
import { EventAdminListLightComponent } from './event-admin-list-light/event-admin-list-light.component';
import {MatCheckboxModule} from "@angular/material/checkbox";


@NgModule({
    declarations: [
        EventAdminStatsComponent,
        EventAdminAddComponent,
        EventAdminTypeAddComponent,
        EventAdminTypeUpdateComponent,
        EventAdminUpdateComponent,
        EventAdminListComponent,
        EventAdminTypeListComponent,
        EventAdminViewComponent,
        EventAdminTypeListLightComponent,
        EventSimpleViewCardComponent,
        EventViewCardComponent,
        EventAdminListLightComponent
    ],
    exports: [
        EventViewCardComponent,
        EventSimpleViewCardComponent,
        EventAdminListLightComponent
    ],
    imports: [
        CommonModule,
        EventAdminRoutingModule,
        MatButtonModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule,
        SharedModule,
        FormsModule,
        MatDatepickerModule,
        NgxMatDatetimePickerModule,
        ReactiveFormsModule,
        LocalizationAdminModule,
        AbstractsAdminModule,
        MatCardModule,
        MatCheckboxModule
    ]
})
export class EventAdminModule { }
