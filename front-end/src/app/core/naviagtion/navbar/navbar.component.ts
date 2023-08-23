import {Component, EventEmitter, Input, Output} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
import {AuthService} from "../../authentication/auth.service";
import {Router} from "@angular/router";

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
              private router: Router) {
  this.userName = ` ${authService.identityClaims['name']} (${authService.identityClaims['email']})`;
  }
  public onToggleSidenav = () => this.sidenavToggle.emit();


  logout(){
    this.authService.logout();


  }

}
