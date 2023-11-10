import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserMapListComponent} from "./user-map-list/user-map-list.component";
import {UserLocalizationListComponent} from "./user-localization-list/user-localization-list.component";
import {UserLocalizationViewComponent} from "./user-localziation-view/user-localziation-view.component";
import {UserLocalizationComponent} from "./user-localization.component";
import {UserMapViewComponent} from "./user-map-view/user-map-view.component";

const routes: Routes = [
  {path: '',
  component: UserLocalizationComponent,
  children: [
    {path: 'maps', component: UserMapListComponent},
    {path: 'list', component: UserLocalizationListComponent},
    {path: 'view/:localizationID', component: UserLocalizationViewComponent},
    {path: 'maps/view/:mapId', component: UserMapViewComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserLocalizationRoutingModule { }
