import {Component, OnInit} from '@angular/core';
import {CustomersService} from "../../../core/service/customers/customers.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit{
  hide = true;

  customerRegistrationRequest!: FormGroup;
  constructor(
    private customerService: CustomersService,
    private formBuilder: FormBuilder,
    private location: Location,
    private router: Router,

  ) {
  }

  ngOnInit(): void {

    this.customerRegistrationRequest = this.formBuilder.group(
      {
        firstName:['', {validators:[Validators.required]}],
        lastName:['', {validators:[Validators.required]}],
        email:['', {validators:[Validators.required, Validators.email]}],
        // email: new FormControl('',[Validators.required, Validators.email]),
        password:['', {validators:[Validators.required]}],
      }
    )
  }

  createUser( ){
    this.customerService.postCustomerUser(this.customerRegistrationRequest.getRawValue()).subscribe(
      {
        next: () => {
          console.log("Registered Successfully")
          // this.toastr.success('Abstract Created Successfully')
        this.location.back();
        },
        error: err => {
          // this.toastr.error("Something Gone Wrong")
        }
      }
    )

  }


}
