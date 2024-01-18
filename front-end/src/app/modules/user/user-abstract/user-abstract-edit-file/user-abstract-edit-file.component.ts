import { Component } from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";

@Component({
  selector: 'app-user-abstract-edit-file',
  templateUrl: './user-abstract-edit-file.component.html',
  styleUrls: ['./user-abstract-edit-file.component.scss']
})
export class UserAbstractEditFileComponent {
  abstractID: number;
  abstractData: AbstractDTOModel = new AbstractDTOModel();

  constructor(
    private abstractService: AbstractsService,
    private route: ActivatedRoute,

  ) {

    this.abstractID = this.route.snapshot.params['abstractID'];

    this.getAbstract()

  }

  getAbstract(){
    this.abstractService.getAbstractUser(this.abstractID).subscribe(value => {
      this.abstractData = value
    })

  }
}
