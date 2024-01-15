import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {FileRole} from "../../../../core/service/abstracts/models/FileRole";

@Component({
  selector: 'app-user-abstract-panel',
  templateUrl: './user-abstract-panel.component.html',
  styleUrls: ['./user-abstract-panel.component.scss']
})
export class UserAbstractPanelComponent implements OnInit{

  abstractUsersList: Array<AbstractDTOModel>;

  constructor(
    private abstractService: AbstractsService,
  ) {
  }


  ngOnInit(): void {
     this.getListOfAbstracts();
  }


  getListOfAbstracts(){
    this.abstractService.getAbstractListUser().subscribe(value => {
      this.abstractUsersList = value;
    })
  }


  refreshData(){
    this.getListOfAbstracts()
    window.location.reload();

  }
  deleteAbstract(abstract: AbstractDTOModel) {
    this.abstractService.deleteAbstractUser(abstract).subscribe(value => {
      this.getListOfAbstracts()

    }
  )
  }

    protected readonly FileRole = FileRole;
}
