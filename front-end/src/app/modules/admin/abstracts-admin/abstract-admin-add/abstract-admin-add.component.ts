import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {AttachmentFileDTOModel} from "../../../../core/service/abstracts/models/AttachmentFileDTO-model";
import {Router} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-abstract-admin-add',
  templateUrl: './abstract-admin-add.component.html',
  styleUrls: ['./abstract-admin-add.component.scss']
})
export class AbstractAdminAddComponent implements  OnInit{

  abstractForm!: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private abstractService: AbstractsService,
    private router: Router,
    private location: Location
    ) {
  }

  ngOnInit(): void {

    this.abstractForm = this.formBuilder.group(
      {
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
    )


  }


    saveAbstract( ){
      this.abstractService.postAbstractAdmin(this.abstractForm.getRawValue()).subscribe(
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
