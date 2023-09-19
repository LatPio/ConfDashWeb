import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {AbstractDTOModel} from "../../../core/service/abstracts/models/AbstractDTO-model";
import {FileRole} from "../../../core/service/abstracts/models/FileRole";
import {AttachmentFileDTOModel} from "../../../core/service/abstracts/models/AttachmentFileDTO-model";

@Component({
  selector: 'app-abstract-card-view',
  templateUrl: './abstract-card-view.component.html',
  styleUrls: ['./abstract-card-view.component.scss']
})
export class AbstractCardViewComponent implements AfterViewInit{
  @Input() abstract!: AbstractDTOModel;
  filesEmpty: boolean = false;
  graphicalAbstract: any = null;

  constructor() {
  }

  ngOnInit(): void {
  }


  getFirstGraphicalAbstract(){
    let firstGraphAbstract: AttachmentFileDTOModel = this.abstract.files.filter(value => value.fileRole === FileRole.GRAPHICAL_ABSTRACT)[0];
    if (firstGraphAbstract){
      this.filesEmpty = true;
      this.graphicalAbstract = firstGraphAbstract;
    } else {
      this.filesEmpty = false;
      this.graphicalAbstract =null
    }
  }

  ngAfterViewInit(): void {
    this.getFirstGraphicalAbstract()

  }

}
