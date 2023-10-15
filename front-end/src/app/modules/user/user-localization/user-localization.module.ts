import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserLocalizationRoutingModule } from './user-localization-routing.module';
import { UserLocalizationComponent } from './user-localization.component';
import { UserMapListComponent } from './user-map-list/user-map-list.component';
import { UserLocalizationListComponent } from './user-localization-list/user-localization-list.component';


@NgModule({
  declarations: [
    UserLocalizationComponent,
    UserMapListComponent,
    UserLocalizationListComponent
  ],
  imports: [
    CommonModule,
    UserLocalizationRoutingModule
  ]
})
export class UserLocalizationModule { }
