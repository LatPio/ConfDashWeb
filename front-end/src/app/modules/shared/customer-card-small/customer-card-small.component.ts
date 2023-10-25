import {Component, Input} from '@angular/core';
import {CustomerCardDTOModel} from "../../../core/service/customers/models/CustomerCardDTO-model";

@Component({
  selector: 'app-customer-card-small',
  templateUrl: './customer-card-small.component.html',
  styleUrls: ['./customer-card-small.component.scss']
})
export class CustomerCardSmallComponent {

  @Input() customerData!: CustomerCardDTOModel;

}
