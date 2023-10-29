import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {MatDialog} from "@angular/material/dialog";
import {
  AbstractAttachmentFileAddComponent
} from "../../../admin/abstracts-admin/abstract-attachment-file-add/abstract-attachment-file-add.component";
import {UserCustomerPhotoAddComponent} from "../user-customer-photo-add/user-customer-photo-add.component";

@Component({
  selector: 'app-user-customer-photo-add-button',
  templateUrl: './user-customer-photo-add-button.component.html',
  styleUrls: ['./user-customer-photo-add-button.component.scss']
})
export class UserCustomerPhotoAddButtonComponent {

  @Input() customerData!: CustomerAdminDTOModel;
  @Input() update: boolean;
  @Output() event = new EventEmitter();

  constructor(
    public dialog: MatDialog
  ) {
  }

  openAddFileDialog(): void {
    const dialogRef = this.dialog.open(UserCustomerPhotoAddComponent,
      {
        width: '600px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: {id: this.customerData.id, update: this.update }
      }
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.event.emit();
      }
    });
  }
}
