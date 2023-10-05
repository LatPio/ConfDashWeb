import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LocalizationAdminComponent} from "./localization-admin.component";
import {LocalizationAdminListComponent} from "./localization-admin-list/localization-admin-list.component";
import {LocalizationAdminAddComponent} from "./localization-admin-add/localization-admin-add.component";
import {LocalizationAdminEditComponent} from "./localization-admin-edit/localization-admin-edit.component";
import {LocalizationAdminViewComponent} from "./localization-admin-view/localization-admin-view.component";
import {
  LocalizationAdminBookingAddComponent
} from "./localization-admin-booking-add/localization-admin-booking-add.component";
import {
  LocalizationAdminBookingListComponent
} from "./localization-admin-booking-list/localization-admin-booking-list.component";
import {
  LocalizationAdminBookingEditComponent
} from "./localization-admin-booking-edit/localization-admin-booking-edit.component";
import {LocalizationAdminStatsComponent} from "./localization-admin-stats/localization-admin-stats.component";
import {MapImageAddComponent} from "./map-image-add/map-image-add.component";
import {LocalizationAdminMapListComponent} from "./localization-admin-map-list/localization-admin-map-list.component";
import {MapImageViewComponent} from "./map-image-view/map-image-view.component";
import {MapImageUpdateComponent} from "./map-image-update/map-image-update.component";

const routes: Routes = [
  {path:'',
  component: LocalizationAdminComponent,
  children:[
    {path: 'stats', component:LocalizationAdminStatsComponent},
    {path: 'list', component: LocalizationAdminListComponent},
    {path: 'add', component: LocalizationAdminAddComponent},
    {path: 'update/:localizationID', component: LocalizationAdminEditComponent},
    {path: 'get/:localizationID', component: LocalizationAdminViewComponent},
    {path: 'booking/add', component: LocalizationAdminBookingAddComponent},
    {path: 'booking/list', component: LocalizationAdminBookingListComponent},
    {path: 'booking/update/:bookingID', component: LocalizationAdminBookingEditComponent},
    {path: 'map/add', component: MapImageAddComponent},
    {path: 'map/list', component: LocalizationAdminMapListComponent},
    {path: 'map/view/:mapId', component: MapImageViewComponent},
    {path: 'map/update/:mapId', component: MapImageUpdateComponent}

  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalizationAdminRoutingModule { }
