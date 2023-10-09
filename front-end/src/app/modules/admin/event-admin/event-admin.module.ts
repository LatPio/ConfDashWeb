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
import { EventAdminCardComponent } from './event-admin-card/event-admin-card.component';
import { EventAdminViewComponent } from './event-admin-view/event-admin-view.component';


@NgModule({
  declarations: [
    EventAdminStatsComponent,
    EventAdminAddComponent,
    EventAdminTypeAddComponent,
    EventAdminTypeUpdateComponent,
    EventAdminUpdateComponent,
    EventAdminListComponent,
    EventAdminTypeListComponent,
    EventAdminCardComponent,
    EventAdminViewComponent
  ],
  imports: [
    CommonModule,
    EventAdminRoutingModule
  ]
})
export class EventAdminModule { }
