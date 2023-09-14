import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {
  AbstractAttachmentFileAddComponent
} from "../abstract-attachment-file-add/abstract-attachment-file-add.component";

@Component({
  selector: 'app-abstract-attachment-file-add-button',
  templateUrl: './abstract-attachment-file-add-button.component.html',
  styleUrls: ['./abstract-attachment-file-add-button.component.scss']
})
export class AbstractAttachmentFileAddButtonComponent {

  @Input() abstract!: AbstractDTOModel;
  @Output() event = new EventEmitter();

  constructor(public dialog: MatDialog) {
  }

  openAddFileDialog(): void {
    const dialogRef = this.dialog.open(AbstractAttachmentFileAddComponent,
      {
        width: '600px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: this.abstract // Pass the item's name or details
      }
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.event.emit();
      }
    });
  }
}
