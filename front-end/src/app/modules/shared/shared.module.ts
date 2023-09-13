import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import { AbstractSheetViewComponent } from './abstract-sheet-view/abstract-sheet-view.component';



@NgModule({
  declarations: [
    DeleteDialogComponent,
    AbstractSheetViewComponent
  ],
  exports: [
    AbstractSheetViewComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    RouterLink,

  ]
})
export class SharedModule { }
