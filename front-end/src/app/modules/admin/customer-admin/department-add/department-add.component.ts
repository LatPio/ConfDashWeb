import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DepartmentService} from "../../../../core/service/customers/department.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {InstitutionService} from "../../../../core/service/customers/institution.service";
import {InstitutionDTOModel} from "../../../../core/service/customers/models/InstitutionDTO-model";

@Component({
  selector: 'app-department-add',
  templateUrl: './department-add.component.html',
  styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit{

  departmentForm!: FormGroup;
  institutionList!: Array<InstitutionDTOModel>;

  selectedInstitution: InstitutionDTOModel;
  constructor(
    private formBuilder: FormBuilder,
    private departmentService: DepartmentService,
    private institutionService: InstitutionService,
    private router: Router,
    private location: Location
  ) {
  }

  ngOnInit(): void {
    this.getInstitutions();

    this.departmentForm = this.formBuilder.group(
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

  getInstitutions(){
    this.institutionService.getInstitutionList().subscribe(value =>
      this.institutionList= value)
  }

  saveAbstract( ){
    this.departmentService.postDepartment(this.departmentForm.getRawValue()).subscribe(
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

  addInstitution(name: string){
    let institution = new InstitutionDTOModel();
    institution.name = name;
    this.institutionService.postInstitution(institution).subscribe(
      value => {
        this.getInstitutions();
      }
    )
  }

  selected($event: InstitutionDTOModel) {
    this.selectedInstitution = $event
    this.departmentForm.get('institution.id')?.setValue(this.selectedInstitution.id);
    this.departmentForm.get('institution.name')?.setValue(this.selectedInstitution.name);

  }
}
