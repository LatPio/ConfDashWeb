import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractsAttachmentFileService} from "../../../../core/service/abstracts/abstracts-attachment-file.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-user-abstract-attach-file',
  templateUrl: './user-abstract-attach-file.component.html',
  styleUrls: ['./user-abstract-attach-file.component.scss']
})
export class UserAbstractAttachFileComponent implements OnInit{

  fileForm!: FormGroup;
  file!: File;
  constructor(
    private formBuilder: FormBuilder,
    private fileService: AbstractsAttachmentFileService,
    public dialogRef: MatDialogRef<UserAbstractAttachFileComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any

  ) {
  }

  ngOnInit(): void {
    this.fileForm = this.formBuilder.group(
      {
        fileRole:[this.data.role],
        abstractsEntity: this.formBuilder.group(
          {id:[this.data.abstract.id]}
        )

      }
    )
  }
  onChange(event: any) {
    this.file = event.target.files[0];
  }
  addFile(){
    this.fileService.postSaveFileUser(this.file ,this.fileForm.getRawValue()).subscribe(

    )
    this.dialogRef.close(true);

  }

  onCancelClick(): void {
    this.dialogRef.close(false);
  }


}
