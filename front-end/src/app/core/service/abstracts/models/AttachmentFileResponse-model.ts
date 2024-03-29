import {FileRole} from "./FileRole";

export class AttachmentFileResponseModel{
  id: number;
  name: string;
  type: string;
  url: string;
  accepted: boolean;
  data: Blob;
  fileRole: FileRole;
}
