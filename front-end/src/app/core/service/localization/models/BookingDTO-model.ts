import {LocalizationDTOModel} from "./LocalizationDTOModel";

export class BookingDTOModel{
  id: number;
  eventIDDat: number;
  locationConflict: boolean;
  timeConflict: boolean;
  dateStart: Date;
  dateEnd: Date;
  localization: LocalizationDTOModel;

}
