import {Component, Input} from '@angular/core';
import {AbstractDTOModel} from "../../../core/service/abstracts/models/AbstractDTO-model";

@Component({
  selector: 'app-abstract-sheet-view',
  templateUrl: './abstract-sheet-view.component.html',
  styleUrls: ['./abstract-sheet-view.component.scss']
})
export class AbstractSheetViewComponent {

  @Input() abstract!: AbstractDTOModel;

}
