import {AttachmentFileDTOModel} from "./AttachmentFileDTO-model";

export class AbstractDTOModel{

  id: number;
  abstractTitle: string;
  body: string;
  authors: string;
  affiliation: string;
  ownerId: number;
  authId: string;
  accepted: boolean;
  files: AttachmentFileDTOModel[];
  comments: string;


}


