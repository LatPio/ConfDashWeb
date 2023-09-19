import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AttachmentFileDTOModel} from "../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {AbstractsAttachmentFileService} from "../../../core/service/abstracts/abstracts-attachment-file.service";

@Component({
  selector: 'app-abstract-file-view',
  templateUrl: './abstract-file-view.component.html',
  styleUrls: ['./abstract-file-view.component.scss']
})
export class AbstractFileViewComponent {

  @Input() figure!: AttachmentFileDTOModel;
  @Output() refreshEvent = new EventEmitter();
  @Input() showOptions: boolean = true
  constructor(
    private fileService:AbstractsAttachmentFileService
  ) {
  }
  deleteFile(id: number){
    this.fileService.deleteFile(id).subscribe(() => {
      this.refreshEvent.emit();

    })
  }

}
