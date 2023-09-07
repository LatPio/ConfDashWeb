import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../../authentication/auth.service";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit{
  ngOnInit(): void {
    this.dialogRef.updateSize('500px','350px')
    this.dialogRef.updatePosition({right:'2%', top: '70px'})
  }

  email: string;
  userName: string;

  constructor(
    public dialogRef: MatDialogRef<UserMenuComponent>,
    private authService: AuthService
  ) {
    this.userName = authService.identityClaims['given_name'];
    this.email = authService.identityClaims['email'];
  }

  onCancelClick(): void {
    this.dialogRef.close(false);
  }

  logout(){
    this.authService.logout();
  }

  protected readonly close = close;
}
