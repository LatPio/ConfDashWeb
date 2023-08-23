export class AttachmentFileDTOModel{
  id :number;
  name: string;
  type: string;
  authId: string;
  fileRole:string;
  accepted: boolean;
  data: Blob;
}


