import {AbstractDTOModel} from "./AbstractDTO-model";
import {FileRole} from "./FileRole";

export class AttachmentFileAdminRequestModel {
  fileRole: FileRole;
  abstracts: AbstractDTOModel;
  accepted: boolean;
  authId: String;

}
