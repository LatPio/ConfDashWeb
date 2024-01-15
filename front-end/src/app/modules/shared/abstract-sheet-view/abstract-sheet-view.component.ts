import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {AbstractDTOModel} from "../../../core/service/abstracts/models/AbstractDTO-model";
import {AttachmentFileDTOModel} from "../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {FileRole} from "../../../core/service/abstracts/models/FileRole";
import {AbstractsService} from "../../../core/service/abstracts/abstracts.service";

@Component({
  selector: 'app-abstract-sheet-view',
  templateUrl: './abstract-sheet-view.component.html',
  styleUrls: ['./abstract-sheet-view.component.scss']
})
export class AbstractSheetViewComponent implements OnInit, AfterViewInit{

  @Input() abstract!: AbstractDTOModel;
  @Input() abstractID! : number;
  filesEmpty: boolean = false;
  graphicalAbstract: any = null;
  image: any = null;

  constructor(
    private abstractService: AbstractsService,
  ) {
  }
  ngOnInit(): void {
    this.getAbstract()
    this.getFirstGraphicalAbstract()

  }

  ngAfterViewInit(): void {
    this.getFirstGraphicalAbstract()

  }


  getAbstract(){
    this.abstractService.getAbstractUser(this.abstractID).subscribe(value => {
      this.abstract = value
    })
  }
  getFirstGraphicalAbstract(){
    let firstGraphAbstract: AttachmentFileDTOModel = this.abstract.files.filter(value => value.fileRole === FileRole.GRAPHICAL_ABSTRACT)[0];
    if (firstGraphAbstract){
      this.filesEmpty = false;
      this.graphicalAbstract = firstGraphAbstract;
    } else {
      this.filesEmpty = true;
      this.graphicalAbstract = null
    }
  }


}
