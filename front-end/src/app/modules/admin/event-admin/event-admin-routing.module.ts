import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventAdminComponent} from "./event-admin.component";
import {EventAdminStatsComponent} from "./event-admin-stats/event-admin-stats.component";
import {EventAdminAddComponent} from "./event-admin-add/event-admin-add.component";
import {EventAdminListComponent} from "./event-admin-list/event-admin-list.component";
import {EventAdminUpdateComponent} from "./event-admin-update/event-admin-update.component";
import {EventAdminTypeAddComponent} from "./event-admin-type-add/event-admin-type-add.component";
import {EventAdminTypeListComponent} from "./event-admin-type-list/event-admin-type-list.component";
import {EventAdminTypeUpdateComponent} from "./event-admin-type-update/event-admin-type-update.component";
import {EventAdminViewComponent} from "./event-admin-view/event-admin-view.component";

const routes: Routes = [
  {path:'',
  component: EventAdminComponent,
  children:[
    {path: 'stats', component: EventAdminStatsComponent},
    {path: 'add', component: EventAdminAddComponent},
    {path: 'assign/:abstractID', component: EventAdminAddComponent},
    {path: 'list', component: EventAdminListComponent},
    {path: 'get/:eventID', component: EventAdminViewComponent},
    {path: 'update/:eventID', component: EventAdminUpdateComponent},
    {path: 'type/add' ,component: EventAdminTypeAddComponent},
    {path: "type/list", component:EventAdminTypeListComponent},
    {path: 'type/update/:eventTypeID', component:EventAdminTypeUpdateComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventAdminRoutingModule { }
