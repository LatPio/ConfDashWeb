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
        id:[''],
        abstractTitle: ['', {validators:[Validators.required]}],
        body: ['', {validators:[Validators.required]}],
        authors: ['', {validators:[Validators.required]}],
        affiliation: ['', {validators:[Validators.required]}],
        presenterId: ['', {validators:[Validators.required]}],
        ownerId: ['', {validators:[Validators.required]}],
        authId: ['', {validators:[Validators.required]}],
        accepted: [false, {validators:[Validators.required]}],
        files: [''],
        comments: ['']
      }
    );
    this.getAbstract();



  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstractForm = this.formBuilder.group(
        {
          id:[{value: this.abstractID, disabled:true}],
          abstractTitle: [value.abstractTitle ],
          body: [value.body],
          authors: [value.authors],
          affiliation: [value.affiliation],
          presenterId: [value.presenterId],
          ownerId: [value.ownerId],
          authId: [value.authId],
          accepted: [value.accepted],
          files: [value.files],
          comments: [value.comments]
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

  config: any = {
    placeholder: '',
    tabsize: 2,
    height: '200px',
    uploadImagePath: '/api/upload',
    toolbar: [
      ['misc', [ 'undo', 'redo']],
      ['style', ['bold', 'italic', 'underline', 'clear']],
      ['font', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
      ['fontsize', ['fontname', 'fontsize', 'color']],
      ['para', ['style', 'ul', 'ol', 'paragraph', 'height']],
      ['insert', ['table', 'link', 'hr']]
    ],
    fontNames: ['Helvetica', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Roboto', 'Times']
  }
}
