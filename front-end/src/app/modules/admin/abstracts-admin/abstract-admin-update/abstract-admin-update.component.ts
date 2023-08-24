import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute, Router} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-abstract-admin-update',
  templateUrl: './abstract-admin-update.component.html',
  styleUrls: ['./abstract-admin-update.component.scss']
})
export class AbstractAdminUpdateComponent {

  abstractForm!: FormGroup;
  abstractID: number;

  constructor(
    private abstractService: AbstractsService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {
    this.abstractID = this.route.snapshot.params['abstractID'];

    this.abstractForm = this.formBuilder.group(
      {
        id:[this.abstractID],
        abstractTitle: ['', {validators:[Validators.required]}],
        body: ['', {validators:[Validators.required]}],
        authors: ['', {validators:[Validators.required]}],
        affiliation: ['', {validators:[Validators.required]}],
        presenterId: ['', {validators:[Validators.required]}],
        ownerId: ['', {validators:[Validators.required]}],
        authId: ['', {validators:[Validators.required]}],
        accepted: [false, {validators:[Validators.required]}],
        // files: [''],
      }
    );
    this.getAbstract();



  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstractForm = this.formBuilder.group(
        {
          id:[this.abstractID],
          abstractTitle: [value.abstractTitle ],
          body: [value.body],
          authors: [value.authors],
          affiliation: [value.affiliation],
          presenterId: [value.presenterId],
          ownerId: [value.ownerId],
          authId: [value.authId],
          accepted: [value.accepted],
        }
      )
    })

  }

  updateAbstract() {
console.log(this.abstractForm.getRawValue())
    this.abstractService.putAbstractAdmin(this.abstractForm.getRawValue()).subscribe(
      {
        next: () => {
          // this.toastr.success('Abstract Updated Successfully')
          this.location.back();
        },
        error: err => {
          // this.toastr.error("Something Gone Wrong")
        }
      }
    )

  }
}
