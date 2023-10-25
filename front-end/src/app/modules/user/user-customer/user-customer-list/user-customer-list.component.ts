import { Component } from '@angular/core';
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {CustomerCardDTOModel} from "../../../../core/service/customers/models/CustomerCardDTO-model";

@Component({
  selector: 'app-user-customer-list',
  templateUrl: './user-customer-list.component.html',
  styleUrls: ['./user-customer-list.component.scss']
})
export class UserCustomerListComponent {

  customerList: Array<CustomerCardDTOModel>
  constructor(
    private customerService: CustomersService
  ) {
  this.getCustomers();
  }

  getCustomers(){
    this.customerService.getCustomersUSerCardList().subscribe(value =>
    {
      this.customerList = value;
    })
  }
}
