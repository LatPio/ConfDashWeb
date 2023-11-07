import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserCalendarComponent} from "./user-calendar.component";
import {UserCalendarViewComponent} from "./user-calendar-view/user-calendar-view.component";

const routes: Routes = [
  {path:'',
  component: UserCalendarComponent,
  children:[
    {path:'view', component: UserCalendarViewComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserCalendarRoutingModule { }
