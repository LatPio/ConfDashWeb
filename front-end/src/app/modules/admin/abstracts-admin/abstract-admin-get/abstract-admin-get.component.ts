import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";

@Component({
  selector: 'app-abstract-admin-get',
  templateUrl: './abstract-admin-get.component.html',
  styleUrls: ['./abstract-admin-get.component.scss']
})
export class AbstractAdminGetComponent implements OnInit{

  // abstractForm!: FormGroup;
  abstractID: number;
  abstractData!: AbstractDTOModel;

  constructor(
    private abstractService: AbstractsService,
    // private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.abstractID = this.route.snapshot.params['abstractID'];

  }

  ngOnInit(): void {
    this.getAbstract();
  }

  getAbstract(){
    this.abstractService.getAbstractAdmin(this.abstractID).subscribe(value => {
      this.abstractData = value;
    });
  }



}
