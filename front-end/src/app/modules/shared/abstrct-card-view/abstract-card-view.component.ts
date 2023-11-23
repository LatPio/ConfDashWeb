import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {AbstractDTOModel} from "../../../core/service/abstracts/models/AbstractDTO-model";
import {FileRole} from "../../../core/service/abstracts/models/FileRole";
import {AttachmentFileDTOModel} from "../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {AbstractsAttachmentFileService} from "../../../core/service/abstracts/abstracts-attachment-file.service";
import {AbstractsService} from "../../../core/service/abstracts/abstracts.service";
import {CustomersService} from "../../../core/service/customers/customers.service";

@Component({
  selector: 'app-abstract-card-view',
  templateUrl: './abstract-card-view.component.html',
  styleUrls: ['./abstract-card-view.component.scss']
})
export class AbstractCardViewComponent implements OnInit,AfterViewInit{

  @Input() abstract!: AbstractDTOModel;
  @Input() abstractID! : number;
  filesEmpty: boolean = false;
  graphicalAbstract: any = null;
  image: any = null;
  @Input() showButtons: boolean = true;
  @Input() widthOfCard: number = 700;
  blob: Blob;
  constructor(
    private abstractService: AbstractsService,
    private customerService: CustomersService,
    private fileService:AbstractsAttachmentFileService) {
  }
  ngOnInit(): void {
    this.getAbstract()
    this.getUSerPhoto()
  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstract = value
    })
  }

  getUSerPhoto(){
    this.customerService.getCustomersUserCard(this.abstract.ownerId).subscribe(value => {
      this.image = value.photo.data
    })
  }

  getBigImg(id: number){
    this.fileService.getDownloadFile(id).subscribe(
      data=>{
        this.blob = new Blob([data], {type: 'application/pdf'});

        var downloadURL = window.URL.createObjectURL(data);
        var link = document.createElement('a');
        link.href = downloadURL;
        link.download = "help.pdf";
        link.click();
      }
    )
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
    this.getUSerPhoto()

  }


  protected readonly Number = Number;
}
