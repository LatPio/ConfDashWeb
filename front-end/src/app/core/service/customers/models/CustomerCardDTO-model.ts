import {InformationLinksDTOModel} from "./InformationLinksDTO-model";
import {DepartmentDTOModel} from "./DepartmentDTO-model";
import {ProfilePhotoDTOModel} from "./ProfilePhotoDTO-model";

export class CustomerCardDTOModel{
  id: number;
  firstName: string;
  lastName: string;
  degree: string;
  phoneNumber: string;
  links: InformationLinksDTOModel;
  department: DepartmentDTOModel;
  photo: ProfilePhotoDTOModel;
}
