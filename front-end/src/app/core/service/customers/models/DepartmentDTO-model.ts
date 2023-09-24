import {InstitutionDTOModel} from "./InstitutionDTO-model";

export class DepartmentDTOModel{
  id: number;
  name: string;
  street: string;
  buildingNumber: string;
  flatNumber: string;
  city: string;
  postalCode: string;
  country: string;
  institution: InstitutionDTOModel
}
