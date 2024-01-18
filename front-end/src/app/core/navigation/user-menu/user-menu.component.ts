import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../../authentication/auth.service";
import {CustomersService} from "../../service/customers/customers.service";
import {CustomerAdminDTOModel} from "../../service/customers/models/CustomerAdminDTO-model";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit{

  personalInfo: CustomerAdminDTOModel = new CustomerAdminDTOModel();

  ngOnInit(): void {
    this.dialogRef.updateSize('500px','350px')
    this.dialogRef.updatePosition({right:'2%', top: '70px'})
    this.customerService.getPersonalInfo().subscribe(value => {this.personalInfo =value})
  }

  email: string;
  userName: string;

  constructor(
    public dialogRef: MatDialogRef<UserMenuComponent>,
    private authService: AuthService,
    private customerService:CustomersService

  ) {
    this.userName = authService.identityClaims['given_name'];
    this.email = authService.identityClaims['email'];
  }

  onCancelClick(): void {
    this.dialogRef.close(false);
  }

  logout(){
    this.authService.logout();
    this.dialogRef.close(false);
  }

  protected readonly close = close;
}
