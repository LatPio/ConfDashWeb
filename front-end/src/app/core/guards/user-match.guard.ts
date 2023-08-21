import {CanMatchFn, Router} from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const userMatchGuard: CanMatchFn = (route, segments) => {
  const authService: AuthService = inject(AuthService);
  const router = inject(Router);
  if (authService.tokenClaimsContains('USER')){
    return authService.tokenClaimsContains('USER');
  } else {
    return router.createUrlTree([''])
  }
};
