import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InvoiceDataService} from "../../../../core/service/customers/invoice-data.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-invoice-data-add',
  templateUrl: './invoice-data-add.component.html',
  styleUrls: ['./invoice-data-add.component.scss']
})
export class InvoiceDataAddComponent implements OnInit{

  invoiceForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private invoiceDataService: InvoiceDataService,
    private router: Router,
    private location: Location
  ) {
  }

  ngOnInit(): void {

    this.invoiceForm = this.formBuilder.group(
      {
        name:['', {validators:[Validators.required]}],
        street:['', {validators:[Validators.required]}],
        buildingNumber:['', {validators:[Validators.required]}],
        flatNumber:['', {validators:[Validators.required]}],
        city:['', {validators:[Validators.required]}],
        postalCode:['', {validators:[Validators.required]}],
        country:['', {validators:[Validators.required]}],
        institution: this.formBuilder.group(
          {
            id:[ '', {validators:[Validators.required]}
            ],
            name:['', {validators:[Validators.required]}]
  }
        )


      }
    )
  }



  saveAbstract( ){
    this.invoiceDataService.postInvoice(this.invoiceForm.getRawValue()).subscribe(
      {
        next: () => {

          this.location.back();
        },
        error: err => {
        }
      }
    )
  }
}
