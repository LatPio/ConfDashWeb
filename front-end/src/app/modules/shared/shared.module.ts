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
import { AbstractCardViewComponent } from './abstrct-card-view/abstract-card-view.component';
import {MatCardModule} from "@angular/material/card";
import { SnackbarErrorComponent } from './snackbar-error/snackbar-error.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { SnackbarMessageComponent } from './snackbar-message/snackbar-message.component';



@NgModule({
  declarations: [
    DeleteDialogComponent,
    AbstractSheetViewComponent,
    DeleteButtonComponent,
    AbstractFileViewComponent,
    AbstractCardViewComponent,
    SnackbarErrorComponent,
    SnackbarMessageComponent
  ],
  exports: [
    AbstractSheetViewComponent,
    DeleteButtonComponent,
    AbstractFileViewComponent,
    AbstractCardViewComponent
  ],
    imports: [
        CommonModule,
        MatDialogModule,
        MatButtonModule,
        RouterLink,
        MatIconModule,
        MatCardModule,
        MatSnackBarModule,

    ]
})
export class SharedModule { }
