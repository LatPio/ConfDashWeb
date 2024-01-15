import { Component } from '@angular/core';
import {AuthService} from "../../core/authentication/auth.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  isAuthenticated: Observable<boolean>;
  isDoneLoading: Observable<boolean>;
  canActivateProtectedRoutes: Observable<boolean>;


  constructor(private authService: AuthService,
              private router: Router) {
    this.isAuthenticated = this.authService.isAuthenticated$;
    this.isDoneLoading = this.authService.isDoneLoading$;
    this.canActivateProtectedRoutes = this.authService.canActivateProtectedRoutes$;

  }

  login(){
    this.authService.login();
  }
  admin(){
    this.router.navigateByUrl('/admin').then();
  }
  user(){
    this.router.navigateByUrl('/user').then();
  }

  logout(){
    this.authService.logout();
  }
  get isAdminFromToken(){
    return this.authService.tokenClaimsContains('ADMIN');
  }
  get isUserFromToken(){
    return this.authService.tokenClaimsContains('USER');
  }

  get hasValidToken() { return this.authService.hasValidToken(); }

}
