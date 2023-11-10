import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {AttachmentFileDTOModel} from "../../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {FileRole} from "../../../../core/service/abstracts/models/FileRole";

@Component({
  selector: 'app-user-abstract-global-view',
  templateUrl: './user-abstract-global-view.component.html',
  styleUrls: ['./user-abstract-global-view.component.scss']
})
export class UserAbstractGlobalViewComponent implements OnInit{

  abstractData: AbstractDTOModel;
  abstractId: number;
  constructor(
    private abstractService: AbstractsService,
    private activeRoute: ActivatedRoute,
  ) {
  this.abstractId = this.activeRoute.snapshot.params["abstractID"]
  }
  ngOnInit(): void {
    this.getAbstractData();
  }

  getAbstractData(){
    this.abstractService.getAbstractUser(this.abstractId).subscribe(value => {
      this.abstractData = value
    })
  }


}
