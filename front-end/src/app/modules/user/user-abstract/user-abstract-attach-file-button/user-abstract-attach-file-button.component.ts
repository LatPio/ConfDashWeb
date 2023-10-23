import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {MatDialog} from "@angular/material/dialog";
import {UserAbstractAttachFileComponent} from "../user-abstract-attach-file/user-abstract-attach-file.component";
import {FileRole} from "../../../../core/service/abstracts/models/FileRole";

@Component({
  selector: 'app-user-abstract-attach-file-button',
  templateUrl: './user-abstract-attach-file-button.component.html',
  styleUrls: ['./user-abstract-attach-file-button.component.scss']
})
export class UserAbstractAttachFileButtonComponent {

  @Input() abstract!: AbstractDTOModel;
  @Input() role!: FileRole;
  @Output() event = new EventEmitter();


  constructor(
    public dialog: MatDialog
  ) {
  }

  openAddFileDialog(): void {
    const dialogRef = this.dialog.open(UserAbstractAttachFileComponent,
      {
        width: '600px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: {abstract: this.abstract, role: this.role} // Pass the item's name or details
      });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.event.emit();
      }
    });
  }

  protected readonly FileRole = FileRole;
}
