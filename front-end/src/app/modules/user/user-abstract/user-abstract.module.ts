import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserAbstractRoutingModule } from './user-abstract-routing.module';
import { UserAbstractAddComponent } from './user-abstract-add/user-abstract-add.component';
import { UserAbstractEditComponent } from './user-abstract-edit/user-abstract-edit.component';
import { UserAbstractGlobalListComponent } from './user-abstract-global-list/user-abstract-global-list.component';
import { UserAbstractGlobalViewComponent } from './user-abstract-global-view/user-abstract-global-view.component';
import { UserAbstractViewComponent } from './user-abstract-view/user-abstract-view.component';
import { UserAbstractPanelComponent } from './user-abstract-panel/user-abstract-panel.component';


@NgModule({
  declarations: [
    UserAbstractAddComponent,
    UserAbstractEditComponent,
    UserAbstractGlobalListComponent,
    UserAbstractGlobalViewComponent,
    UserAbstractViewComponent,
    UserAbstractPanelComponent
  ],
  imports: [
    CommonModule,
    UserAbstractRoutingModule
  ]
})
export class UserAbstractModule { }
