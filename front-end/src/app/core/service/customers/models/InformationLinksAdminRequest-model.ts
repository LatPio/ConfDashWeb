import {CustomerAdminDTOModel} from "./CustomerAdminDTO-model";

export class InformationLinksAdminRequestModel{
  name: string;
  urlLink: string;
  authId: string;
  customer: CustomerAdminDTOModel
}
