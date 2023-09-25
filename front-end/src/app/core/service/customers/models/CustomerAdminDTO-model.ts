import {InformationLinksUserDTOModel} from "./InformationLinksUserDTOModel";
import {DepartmentDTOModel} from "./DepartmentDTO-model";
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
  department: DepartmentDTOModel;
  photo: ProfilePhotoDTOModel;
}
