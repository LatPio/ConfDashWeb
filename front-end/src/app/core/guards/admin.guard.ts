import { CanActivateFn } from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const adminGuard: CanActivateFn = (route, state) => {

  const authService: AuthService = inject(AuthService);
  if (authService.tokenClaimsContains('ADMIN')){
    return authService.tokenClaimsContains('ADMIN');
  }
  return false;



  };
