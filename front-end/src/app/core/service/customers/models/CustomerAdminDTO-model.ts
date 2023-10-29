import {InformationLinksUserDTOModel} from "./InformationLinksUserDTOModel";
import {InvoiceDataDTOModel} from "./InvoiceDataDTOModel";
import {ProfilePhotoDTOModel} from "./ProfilePhotoDTO-model";

export class CustomerAdminDTOModel{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  degree: string;
  phoneNumber: string;
  authID: string;
  links: Array<InformationLinksUserDTOModel>;
  invoiceData: InvoiceDataDTOModel;
  photo: ProfilePhotoDTOModel;
}
