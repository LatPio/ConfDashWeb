import {Component, OnInit} from '@angular/core';
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-customers-view',
  templateUrl: './customers-view.component.html',
  styleUrls: ['./customers-view.component.scss']
})
export class CustomersViewComponent implements  OnInit{

  customerId: number;
  customerData: CustomerAdminDTOModel;
  // image: any = null;
  constructor(
    private customerService: CustomersService,
    private route: ActivatedRoute,

  ) {

    this.customerId = this.route.snapshot.params['customerID']
  }

  ngOnInit(): void {
    this.getCustomer()
  }

  getCustomer(){
    this.customerService.getCustomerAdmin(this.customerId).subscribe(value => {
      this.customerData = value;
    })
  }

  refreshData(){
    this.getCustomer()
    window.location.reload();

  }

}
