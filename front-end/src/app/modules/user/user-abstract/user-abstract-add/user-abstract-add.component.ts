import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-abstract-add',
  templateUrl: './user-abstract-add.component.html',
  styleUrls: ['./user-abstract-add.component.scss']
})
export class UserAbstractAddComponent implements OnInit {

  abstractForm!: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private abstractService: AbstractsService,
    private router: Router,
    private location: Location,
    private _snackBar: MatSnackBar,

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
        authId: ['', {validators:[Validators.required]}],
      }
    )


  }
  openSnackBarError(message: string) {
    this._snackBar.openFromComponent(SnackbarErrorComponent, {
      data: message,
    });
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackbarMessageComponent, {
      data: message,
    });
  }

  saveAbstract( ){
    this.abstractService.postAbstractUser(this.abstractForm.getRawValue()).subscribe(
      {
        next: () => {
          this.openSnackBar("Abstract Created Successfully!")


          this.location.back();
        },
        error: err => {
          this.openSnackBarError(err.error.detail)

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
