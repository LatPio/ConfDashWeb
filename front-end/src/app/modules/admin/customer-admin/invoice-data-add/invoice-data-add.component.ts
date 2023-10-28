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
  // institutionList!: Array<InstitutionDTOModel>;

  // selectedInstitution: InstitutionDTOModel;
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

  // getInstitutions(){
  //   this.institutionService.getInstitutionList().subscribe(value =>
  //     this.institutionList= value)
  // }

  saveAbstract( ){
    this.invoiceDataService.postInvoice(this.invoiceForm.getRawValue()).subscribe(
      {
        next: () => {

          // this.toastr.success('Abstract Created Successfully')
          this.location.back();
        },
        error: err => {
          // this.toastr.error("Something Gone Wrong")
        }
      }
    )

  }



  // selected($event: InstitutionDTOModel) {
  //   this.selectedInstitution = $event
  //   this.departmentForm.get('institution.id')?.setValue(this.selectedInstitution.id);
  //   this.departmentForm.get('institution.name')?.setValue(this.selectedInstitution.name);
  //
  // }
}
