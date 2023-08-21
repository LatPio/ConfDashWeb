import { CanActivateFn } from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const isAuthenticatedGuard: CanActivateFn = (route, state) => {
  var authService: AuthService = inject(AuthService);

  return authService.isAuthenticated$;
};
