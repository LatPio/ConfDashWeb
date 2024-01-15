import {Component, OnInit} from '@angular/core';
import {CustomerStatsResponseModel} from "../../../../core/service/customers/models/CustomerStatsResponseModel";
import {CustomersService} from "../../../../core/service/customers/customers.service";

@Component({
  selector: 'app-customer-stats',
  templateUrl: './customer-stats.component.html',
  styleUrls: ['./customer-stats.component.scss']
})
export class CustomerStatsComponent implements OnInit{

  customerStats!: CustomerStatsResponseModel;

  constructor(
    private customerService: CustomersService
  ) {
  }

  ngOnInit(): void {
    this.getStats()
  }

  getStats(){
    this.customerService.getCustomersStatsAdmin().subscribe(value => {
      this.customerStats = value;
    })
  }
}
