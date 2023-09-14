import {Component, Input} from '@angular/core';
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";

@Component({
  selector: 'app-abstract-analytical-data',
  templateUrl: './abstract-analytical-data.component.html',
  styleUrls: ['./abstract-analytical-data.component.scss']
})
export class AbstractAnalyticalDataComponent {

  @Input() abstract!: AbstractDTOModel;


}
