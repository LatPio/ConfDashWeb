import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventAdminComponent} from "./event-admin.component";
import {EventAdminStatsComponent} from "./event-admin-stats/event-admin-stats.component";

const routes: Routes = [
  {path:'',
  component: EventAdminComponent,
  children:[
    {path: 'stats', component: EventAdminStatsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventAdminRoutingModule { }
