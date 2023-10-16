import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserAbstractModule} from "./user-abstract.module";
import {UserAbstractAddComponent} from "./user-abstract-add/user-abstract-add.component";
import {UserAbstractEditComponent} from "./user-abstract-edit/user-abstract-edit.component";
import {UserAbstractViewComponent} from "./user-abstract-view/user-abstract-view.component";
import {UserAbstractPanelComponent} from "./user-abstract-panel/user-abstract-panel.component";
import {UserAbstractGlobalListComponent} from "./user-abstract-global-list/user-abstract-global-list.component";
import {UserAbstractGlobalViewComponent} from "./user-abstract-global-view/user-abstract-global-view.component";
import {UserAbstractComponent} from "./user-abstract.component";

const routes: Routes = [
  {path:'',
  component: UserAbstractComponent,
  children:[
    {path: 'add', component: UserAbstractAddComponent},
    {path: 'edit', component: UserAbstractEditComponent},
    {path: 'view', component: UserAbstractViewComponent},
    {path: 'panel', component: UserAbstractPanelComponent},
    {path: 'list', component: UserAbstractGlobalListComponent},
    {path: 'list/view', component: UserAbstractGlobalViewComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserAbstractRoutingModule { }
