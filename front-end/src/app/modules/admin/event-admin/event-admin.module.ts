import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventAdminRoutingModule } from './event-admin-routing.module';
import { EventAdminStatsComponent } from './event-admin-stats/event-admin-stats.component';


@NgModule({
  declarations: [
    EventAdminStatsComponent
  ],
  imports: [
    CommonModule,
    EventAdminRoutingModule
  ]
})
export class EventAdminModule { }
