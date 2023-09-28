import {AfterViewInit, Component, EventEmitter, Input, Output} from '@angular/core';
import {InstitutionDTOModel} from "../../../../core/service/customers/models/InstitutionDTO-model";
import {MatTableDataSource} from "@angular/material/table";
import {AbstractDTOModel} from "../../../../core/service/abstracts/models/AbstractDTO-model";
import {InstitutionService} from "../../../../core/service/customers/institution.service";

@Component({
  selector: 'app-institution-list',
  templateUrl: './institution-list.component.html',
  styleUrls: ['./institution-list.component.scss']
})
export class InstitutionListComponent implements AfterViewInit{

  @Input() set institutionList(value:Array<InstitutionDTOModel>){
    this.dataSource = new MatTableDataSource<InstitutionDTOModel>(value)
  } ;
  @Output() selectedInstitution = new EventEmitter<InstitutionDTOModel>;
  @Output() refresh = new EventEmitter;


  dataSource = new MatTableDataSource<InstitutionDTOModel>();

  displayedColumns: string[] = ['id', 'name'];

  select(institution: InstitutionDTOModel) {
    this.selectedInstitution.emit(institution);
  }

  ngAfterViewInit(): void {
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  constructor(
    private institutionService: InstitutionService
  ) {
  }

  addInstitution(name: string){
    let institution = new InstitutionDTOModel();
    institution.name = name;
    this.institutionService.postInstitution(institution).subscribe(
      value => {
        // this.getInstitutions();
        this.refresh.emit();
      }
    )
  }
}
