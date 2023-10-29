import {Component, OnInit} from '@angular/core';
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";

@Component({
  selector: 'app-user-customer-profile',
  templateUrl: './user-customer-profile.component.html',
  styleUrls: ['./user-customer-profile.component.scss']
})
export class UserCustomerProfileComponent implements OnInit{

  personalInfo: CustomerAdminDTOModel
  constructor(
    private customerService:CustomersService
  ) {
  }
  ngOnInit(): void {
    this.getProfile()
  }


  private getProfile() {
    this.customerService.getPersonalInfo().subscribe(value => {this.personalInfo =value})
  }

  refreshData(){
    this.getProfile()
    window.location.reload();

  }
}
