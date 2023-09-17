import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import { AbstractSheetViewComponent } from './abstract-sheet-view/abstract-sheet-view.component';
import { DeleteButtonComponent } from './delete-button/delete-button.component';
import {MatIconModule} from "@angular/material/icon";
import { AbstractFileViewComponent } from './abstract-file-view/abstract-file-view.component';



@NgModule({
  declarations: [
    DeleteDialogComponent,
    AbstractSheetViewComponent,
    DeleteButtonComponent,
    AbstractFileViewComponent
  ],
    exports: [
        AbstractSheetViewComponent,
        DeleteButtonComponent,
        AbstractFileViewComponent
    ],
    imports: [
        CommonModule,
        MatDialogModule,
        MatButtonModule,
        RouterLink,
        MatIconModule,

    ]
})
export class SharedModule { }
