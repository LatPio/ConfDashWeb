import {Component, OnInit} from '@angular/core';
import {StatsLocalizationResponseModel} from "../../../../core/service/localization/models/StatsLoclizationResponse-model";
import {LocalizationService} from "../../../../core/service/localization/localization.service";

@Component({
  selector: 'app-localization-admin-stats',
  templateUrl: './localization-admin-stats.component.html',
  styleUrls: ['./localization-admin-stats.component.scss']
})
export class LocalizationAdminStatsComponent implements OnInit{

  localizationStats!: StatsLocalizationResponseModel;

  constructor(
    private localizationService: LocalizationService,

  ) {
  }

  ngOnInit(): void {
    this.getStats();
  }

  getStats(){
    this.localizationService.getLocalizationStats().subscribe(value => {
      this.localizationStats = value;
    })
  }


}
