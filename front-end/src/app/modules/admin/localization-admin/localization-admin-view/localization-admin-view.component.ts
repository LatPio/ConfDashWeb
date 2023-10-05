import {Component, HostBinding, OnInit} from '@angular/core';
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";
import {ActivatedRoute} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-localization-admin-view',
  templateUrl: './localization-admin-view.component.html',
  styleUrls: ['./localization-admin-view.component.scss']
})
export class LocalizationAdminViewComponent implements OnInit{

  localizationId: number;
  localizationData!: LocalizationDTOModel;



  constructor(
    private localizationService: LocalizationService,
    private route: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) {
    this.localizationId = this.route.snapshot.params['localizationID'];

  }

  ngOnInit(): void {
    this.getLocalization();
  }

  getLocalization(){
    this.localizationService.getLocalization(this.localizationId).subscribe(value => {
      this.localizationData = value
    });
  }

}
