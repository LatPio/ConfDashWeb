import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {InformationLinksService} from "../../../../core/service/customers/informationlinks.service";
import {InformationLinksUserDTOModel} from "../../../../core/service/customers/models/InformationLinksUserDTOModel";
import {CustomersService} from "../../../../core/service/customers/customers.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {
  UserCustomerLinksEditDialogComponent
} from "../user-customer-links-edit-dialog/user-customer-links-edit-dialog.component";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";

@Component({
  selector: 'app-user-customer-links-edit',
  templateUrl: './user-customer-links-edit.component.html',
  styleUrls: ['./user-customer-links-edit.component.scss']
})
export class UserCustomerLinksEditComponent implements OnInit{

  displayedColumns: string[] = ['name', 'urlLink', 'options'];
  dataSource = new MatTableDataSource<InformationLinksUserDTOModel>();
  @ViewChild(MatSort) sort: MatSort;

  customerData!: CustomerAdminDTOModel
  constructor(
    private informationLinkService: InformationLinksService,
    private customersService: CustomersService,
    public dialog: MatDialog


  ) {
  }

  ngOnInit() {
    this.getCustomer();
    this.getInformationLinks();
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }
  getCustomer(){
    this.customersService.getPersonalInfo().subscribe(value =>
    this.customerData = value)
  }

  getInformationLinks() {
    this.informationLinkService.getInformationLinkListUser().subscribe(value => {
      this.dataSource.data = value;
    })
  }

  deleteLink(link: InformationLinksUserDTOModel) {
    this.informationLinkService.deleteInformationLinkUser(link.id).subscribe(value =>
      {
        this.getInformationLinks();
      }

    )
  }
  openCreateLinkDialog(update: boolean): void {
    const dialogRef = this.dialog.open(UserCustomerLinksEditDialogComponent,
      {
        width: '600px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: {update: update , customerID: this.customerData.id, authId: this.customerData.authID}// Pass the item's name or details
      });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getInformationLinks();
      }
    });
  }
  openEditLinkDialog(linkData: InformationLinksUserDTOModel, update: boolean): void {
    const dialogRef = this.dialog.open(UserCustomerLinksEditDialogComponent,
      {
        width: '600px',
        enterAnimationDuration: 0,
        panelClass: 'customStyle',

        data: {linkData: linkData, update: update}// Pass the item's name or details
      });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getInformationLinks();
      }
    });
  }
}
