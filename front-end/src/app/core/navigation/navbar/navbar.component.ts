import {Component, EventEmitter, Input, Output} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {AuthService} from "../../authentication/auth.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {UserMenuComponent} from "../user-menu/user-menu.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  @Output() public sidenavToggle = new EventEmitter();

  @Input() public title = '';

  userName: string= '';
  constructor(private authService: AuthService,
              private router: Router,
              public dialog: MatDialog) {
  this.userName = ` ${authService.identityClaims['name']} (${authService.identityClaims['email']})`;
  }
  public onToggleSidenav = () => this.sidenavToggle.emit();

  openManuDialog() {
    const dialogRef = this.dialog.open(UserMenuComponent, {
      enterAnimationDuration: 0,
      exitAnimationDuration:0,
      panelClass: 'usermenucontainer'
    })
  }
}
