import {Component, Input, OnInit} from '@angular/core';
import {LocalizationService} from "../../../../core/service/localization/localization.service";
import {LocalizationDTOModel} from "../../../../core/service/localization/models/LocalizationDTOModel";

@Component({
  selector: 'app-localization-card',
  templateUrl: './localization-card.component.html',
  styleUrls: ['./localization-card.component.scss']
})
export class LocalizationCardComponent implements OnInit{

  @Input() localizationId: number
  localization: LocalizationDTOModel

  constructor(
    private localizationService: LocalizationService
  ) {
  }

  ngOnInit(): void {
    this.getLocalization();
  }

  getLocalization(){
    this.localizationService.getLocalization(this.localizationId).subscribe(value =>
      this.localization = value
    )
  }


}
