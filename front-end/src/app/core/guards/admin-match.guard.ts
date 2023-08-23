import {CanMatchFn, Router} from '@angular/router';
import {AuthService} from "../authentication/auth.service";
import {inject} from "@angular/core";

export const adminMatchGuard: CanMatchFn = (route, segments) => {
  const authService: AuthService = inject(AuthService);
  const router = inject(Router);
  if (authService.tokenClaimsContains('ADMIN')){
    return authService.tokenClaimsContains('ADMIN');
  } else {
    return router.createUrlTree([''])
  }


};
