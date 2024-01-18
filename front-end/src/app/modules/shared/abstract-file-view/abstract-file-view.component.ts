import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AttachmentFileDTOModel} from "../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {AbstractsAttachmentFileService} from "../../../core/service/abstracts/abstracts-attachment-file.service";
import {FileRole} from "../../../core/service/abstracts/models/FileRole";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-abstract-file-view',
  templateUrl: './abstract-file-view.component.html',
  styleUrls: ['./abstract-file-view.component.scss']
})
export class AbstractFileViewComponent {

  @Input() access: string;
  @Input() figure!: AttachmentFileDTOModel;
  @Output() refreshEvent = new EventEmitter();
  @Input() showOptions: boolean = true
  fileUrl: SafeResourceUrl;
  blob: Blob;

  constructor(
    private fileService:AbstractsAttachmentFileService,
    private sanitizer: DomSanitizer
  ) {
  }
  deleteFile(id: number){
    if(this.access === 'admin'){
      this.fileService.deleteFile(id).subscribe(() => {
        this.refreshEvent.emit();
      })
    }
    if(this.access === 'user'){
      this.fileService.deleteFileUser(id).subscribe(() => {
        this.refreshEvent.emit();
      })
    }
  }

  getFileDownload(id: number){
    this.fileService.getDownloadFile(id).subscribe(
      data=>{
        let blob = new Blob([data], { type: 'application/pdf' });
        let url = window.URL.createObjectURL(blob);
        let anchor = document.createElement("a");
        anchor.download = this.figure.name
        anchor.href = url;
        anchor.click();
      }
    )
  }

  protected readonly FileRole = FileRole;
}
