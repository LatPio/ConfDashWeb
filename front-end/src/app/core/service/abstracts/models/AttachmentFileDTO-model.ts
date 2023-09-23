import {FileRole} from "./FileRole";

export class AttachmentFileDTOModel{
  id: number;
  name: string;
  type: string;
  authId: string;
  accepted: boolean;
  data: Blob;
  smallData: Blob;
  fileRole: FileRole;
}
