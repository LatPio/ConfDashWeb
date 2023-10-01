import {MapImageDTOModel} from "./MapImageDTO-model";

export class LocalizationDTOModel {
  id: number;
  room: string;
  coordinateX: number;
  coordinateY: number;
  mapImage: MapImageDTOModel;

}
