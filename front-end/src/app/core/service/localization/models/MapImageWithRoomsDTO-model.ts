import {LocalizationWithOutMapDTOModel} from "./LocalizationWithOutMapDTO-model";

export class MapImageWithRoomsDTOModel{
  id: number;
  name: string;
  fileName: string;
  data: Blob;
  localization: Array<LocalizationWithOutMapDTOModel>


}
