import {CustomerIdDTOModel} from "./CustomerIdDTO-model";

export class InformationLinksAdminDTOModel {
  id: number;
  name: string;
  urLink: string;
  authId: string;
  customer: CustomerIdDTOModel;
}
