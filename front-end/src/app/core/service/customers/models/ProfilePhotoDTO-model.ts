import {CustomerIdDTOModel} from "./CustomerIdDTO-model";

export class ProfilePhotoDTOModel{
  id: number;
  name: string;
  type: string;
  data: Blob;
  authId: string;
  customer: CustomerIdDTOModel;
}
