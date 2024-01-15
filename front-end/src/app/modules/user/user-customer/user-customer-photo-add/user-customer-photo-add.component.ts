import {Component, Inject, OnInit} from '@angular/core';
import {ProfilePhotoService} from "../../../../core/service/customers/profile-photo.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProfilePhotoDTOModel} from "../../../../core/service/customers/models/ProfilePhotoDTO-model";

@Component({
  selector: 'app-user-customer-photo-add',
  templateUrl: './user-customer-photo-add.component.html',
  styleUrls: ['./user-customer-photo-add.component.scss']
})
export class UserCustomerPhotoAddComponent implements OnInit{

  file!: File;

  constructor(
    private photoService: ProfilePhotoService,
    public dialogRef: MatDialogRef<ProfilePhotoDTOModel>,
    @Inject(MAT_DIALOG_DATA) public customerData: any

  ) {
  }
  ngOnInit(): void {

  }

  putPhoto(){
    this.photoService.putUpdateProfilePhotoUser(this.file ,this.customerData.id).subscribe()
    this.dialogRef.close(true);

  }

  addPhoto(){
    this.photoService.postSaveProfilePhotoUser(this.file ,this.customerData.id).subscribe()
    this.dialogRef.close(true);

  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }
  onCancelClick(): void {
    this.dialogRef.close(false);
  }
}
