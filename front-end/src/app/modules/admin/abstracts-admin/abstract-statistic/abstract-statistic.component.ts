import {Component, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../core/service/abstracts/abstracts.service";
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";
import {StatsResponseModel} from "../../../../core/service/abstracts/models/statsResponse-model";

@Component({
  selector: 'app-abstract-statistic',
  templateUrl: './abstract-statistic.component.html',
  styleUrls: ['./abstract-statistic.component.scss']
})
export class AbstractStatisticComponent implements OnInit{

  abstractStats!: StatsResponseModel;
  constructor(private abstractService: AbstractsService,
              private matIconRegistry: MatIconRegistry,
              private domSanitizer: DomSanitizer
  ) {
    this.matIconRegistry.addSvgIcon(
      `icon_label`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("")
    );
  }

  ngOnInit(): void {
    this.getStats();
  }

  getStats(){
    this.abstractService.getStatsAdmin().subscribe(value => {
      this.abstractStats = value;
    })
  }


}
