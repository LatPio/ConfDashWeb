import {Component, Input, OnInit} from '@angular/core';
import {CustomerCardDTOModel} from "../../../core/service/customers/models/CustomerCardDTO-model";
import {CustomersService} from "../../../core/service/customers/customers.service";

@Component({
  selector: ' app-customer-card-small',
  templateUrl: './customer-card-small.component.html',
  styleUrls: ['./customer-card-small.component.scss']
})
export class CustomerCardSmallComponent implements OnInit{

  @Input() customerData!: CustomerCardDTOModel;

  @Input() authId!: string;

  constructor(
    private customerService: CustomersService
  ) {
  }

  getCustomerData(){
    if(this.customerData === null && this.authId){
      console.log("get data within small card component")
      this.customerService.getCustomerAdminByAuthId(this.authId).subscribe(value => {
        this.customerData = value
      })
    }

  }
  ngOnInit(): void {
   this.getCustomerData()
  }
}
