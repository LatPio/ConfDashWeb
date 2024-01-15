import {AuthConfig} from "angular-oauth2-oidc";

export const authConfig: AuthConfig = {
  issuer: 'http://localhost:8181/realms/confdashweb',
  redirectUri: window.location.origin + '/',
  postLogoutRedirectUri: window.location.origin+'/home',
  clientId: 'angular-frontend',
  responseType: 'code',
  strictDiscoveryDocumentValidation: false,
  scope: 'openid profile email offline_access',
  sessionChecksEnabled: true,
  useSilentRefresh: true,
  disableAtHashCheck: true



}
