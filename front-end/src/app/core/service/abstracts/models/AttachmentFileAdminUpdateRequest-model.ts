import {AbstractDTOModel} from "./AbstractDTO-model";
import {FileRole} from "./FileRole";

export class AttachmentFileAdminUpdateRequestModel{
  id: number;
  fileRole: FileRole;
  abstract: AbstractDTOModel;
  accepted: boolean;
  authId: string;
}
