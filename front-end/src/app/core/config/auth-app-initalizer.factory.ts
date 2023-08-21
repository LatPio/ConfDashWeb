import {AuthService} from "../service/auth.service";

export function authAppInitializerFactory(authService: AuthService): () => Promise<void> {
  return () => authService.runInitialLoginSequence();
}
