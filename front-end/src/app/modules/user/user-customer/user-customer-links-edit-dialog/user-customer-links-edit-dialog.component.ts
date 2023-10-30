import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InformationLinksService} from "../../../../core/service/customers/informationlinks.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-user-customer-links-edit-dialog',
  templateUrl: './user-customer-links-edit-dialog.component.html',
  styleUrls: ['./user-customer-links-edit-dialog.component.scss']
})
export class UserCustomerLinksEditDialogComponent implements OnInit{

  infoLinkForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private infoLinksService: InformationLinksService,
    public dialogRef: MatDialogRef<UserCustomerLinksEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
  ) {
  };
  ngOnInit(): void {
    this.infoLinkForm = this.formBuilder.group(
      {
        id:[''],
        name:['',{validators: [Validators.required]}],
        urlLink:['',{validators: [Validators.required]}],
        authId:[this.data.authId,{validators: [Validators.required]}],
        customer: this.formBuilder.group(
          {
            id:[this.data.customerID,{validators: [Validators.required]}]
          }
        )
      }
    )
    this.patchLinkForm()

  };

  patchLinkForm(){
    this.infoLinkForm.patchValue(this.data.linkData)
  }
  saveInfoLinks(){
    this.infoLinksService.postInformationLinkUser(this.infoLinkForm.getRawValue()).subscribe()
    this.dialogRef.close(true)
  }
  updateInfoLinks(){
    this.infoLinksService.putInformationLinkUser(this.infoLinkForm.getRawValue()).subscribe()
    this.dialogRef.close(true)
  }


  onCancelClick(): void {
    this.dialogRef.close(false);
  }

}
