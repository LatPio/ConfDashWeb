import {CustomerIdDTOModel} from "./CustomerIdDTO-model";

export class InformationLinksUserDTOModel {
  id: number;
  name: string;
  urlLink: string;
  customer: CustomerIdDTOModel;
}