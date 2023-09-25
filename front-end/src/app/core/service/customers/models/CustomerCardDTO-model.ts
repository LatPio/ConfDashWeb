import {InformationLinksUserDTOModel} from "./InformationLinksUserDTOModel";
import {DepartmentDTOModel} from "./DepartmentDTO-model";
import {ProfilePhotoDTOModel} from "./ProfilePhotoDTO-model";

export class CustomerCardDTOModel{
  id: number;
  firstName: string;
  lastName: string;
  degree: string;
  phoneNumber: string;
  links: Array<InformationLinksUserDTOModel>;
  department: DepartmentDTOModel;
  photo: ProfilePhotoDTOModel;
}
