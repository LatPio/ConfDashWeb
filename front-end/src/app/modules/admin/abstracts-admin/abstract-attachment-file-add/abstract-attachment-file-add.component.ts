import {Component, Inject, Input, OnInit} from '@angular/core';
import {AbstractsAttachmentFileService} from "../../../../core/service/abstracts/abstracts-attachment-file.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {FileRole} from "../../../../core/service/abstracts/models/FileRole";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-abstract-attachment-file-add',
  templateUrl: './abstract-attachment-file-add.component.html',
  styleUrls: ['./abstract-attachment-file-add.component.scss']
})
export class AbstractAttachmentFileAddComponent implements OnInit{

  // @Input() abstract!: AbstractDTOModel

  fileRole = Object.values(FileRole);
  fileForm!: FormGroup;
  file!: File;
  constructor(
    private formBuilder: FormBuilder,
    private fileService: AbstractsAttachmentFileService,
    public dialogRef: MatDialogRef<AbstractAttachmentFileAddComponent>,
    @Inject(MAT_DIALOG_DATA) public abstract: AbstractDTOModel

  ) {
  }

  ngOnInit(): void {
    this.fileForm = this.formBuilder.group(
      {
        fileRole:[''],
        abstractsEntity: this.formBuilder.group(
          {id:[this.abstract.id]}
        ),
        accepted: [false],
        authId: [this.abstract.authId]

      }
    )
  }
  onChange(event: any) {
    this.file = event.target.files[0];
  }
  addFile(){
    this.fileService.postSaveFile(this.file ,this.fileForm.getRawValue()).subscribe(

    )
    this.dialogRef.close(true);

  }

  onCancelClick(): void {
    this.dialogRef.close(false);
  }


}
