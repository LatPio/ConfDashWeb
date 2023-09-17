import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";

@Component({
  selector: 'app-delete-button',
  templateUrl: './delete-button.component.html',
  styleUrls: ['./delete-button.component.scss']
})
export class DeleteButtonComponent {

  @Input() name!: string;
  @Output() event = new EventEmitter();

  constructor(public dialog: MatDialog) {
  }

  openDeleteDialog(): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent,
      {
        width: '400px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: { item: this.name } // Pass the item's name or details
      }
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.event.emit();
        console.log('Item deleted');
      }
    });
  }
}
