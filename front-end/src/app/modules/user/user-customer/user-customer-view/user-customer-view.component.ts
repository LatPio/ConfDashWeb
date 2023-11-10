import {Component, OnInit} from '@angular/core';
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {CustomerCardDTOModel} from "../../../../core/service/customers/models/CustomerCardDTO-model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-customer-view',
  templateUrl: './user-customer-view.component.html',
  styleUrls: ['./user-customer-view.component.scss']
})
export class UserCustomerViewComponent implements OnInit {

  userInfo: CustomerCardDTOModel
  userID: number;
  constructor(
    private customerService: CustomersService,
    private activeRoute: ActivatedRoute
  ) {
    this.userID = this.activeRoute.snapshot.params["customerID"];
  }
  ngOnInit(): void {
    this.getUserInformation(this.userID);
  }


  getUserInformation(id: number){
    this.customerService.getCustomersUserCard(id).subscribe(value => {
      this.userInfo = value
    })
  }
}
