import {InformationLinksUserDTOModel} from "./InformationLinksUserDTOModel";
import {InvoiceDataDTOModel} from "./InvoiceDataDTOModel";
import {ProfilePhotoDTOModel} from "./ProfilePhotoDTO-model";

export class CustomerCardDTOModel{
  id: number;
  firstName: string;
  lastName: string;
  degree: string;
  phoneNumber: string;
  links: Array<InformationLinksUserDTOModel>;
  invoice: InvoiceDataDTOModel;
  photo: ProfilePhotoDTOModel;
}
