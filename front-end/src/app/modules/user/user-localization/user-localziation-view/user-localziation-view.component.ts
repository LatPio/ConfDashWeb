import {Component, OnInit} from '@angular/core';
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {ActivatedRoute} from "@angular/router";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {verifyHostBindings} from "@angular/compiler";

@Component({
  selector: 'app-user-localization-view',
  templateUrl: './user-localization-view.component.html',
  styleUrls: ['./user-localization-view.component.scss']
})
export class UserLocalizationViewComponent implements OnInit{

  localizationId: number;
  localizationData: LocalizationDTOModel;
  constructor(
    private localizationService: LocalizationService,
    private activeRoute: ActivatedRoute
  ) {
    this.localizationId = this.activeRoute.snapshot.params["localizationID"]

  }
  ngOnInit(): void {
    this.getLocalization();
  }


  getLocalization() {
    this.localizationService.getLocalization(this.localizationId).subscribe(value => {
      this.localizationData = value;
      }

    )
  }
}
