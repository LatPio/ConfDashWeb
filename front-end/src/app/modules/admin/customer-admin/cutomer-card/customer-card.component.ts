import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CustomerCardDTOModel} from "../../../../core/service/customers/models/CustomerCardDTO-model";
import {Router} from "@angular/router";
import {CustomersService} from "../../../../core/service/customers/customers.service";

@Component({
  selector: 'app-customer-card',
  templateUrl: './customer-card.component.html',
  styleUrls: ['./customer-card.component.scss']
})
export class CustomerCardComponent  implements OnInit{

  @Input() customerData: CustomerCardDTOModel;
  @Input() customerId: number;

  constructor(
    private customerService: CustomersService,
    private router: Router
  ) {
  }

  getCustomer(){
  this.customerService.getCustomersUserCard(this.customerId).subscribe(value => {
      this.customerData = value;
    })
  }

  customerView() {
    this.router.navigate(["/admin/customer/view/", this.customerData.id]).then()
  }

  ngOnInit(): void {
    this.getCustomer()

  }
}
