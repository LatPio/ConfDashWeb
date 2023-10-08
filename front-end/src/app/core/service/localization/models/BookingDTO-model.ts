import {LocalizationDTOModel} from "./LocalizationDTOModel";

export class BookingDTOModel{
  id: number;
  eventIDData: number;
  locationConflict: boolean;
  timeConflict: boolean;
  dateStart: Date;
  dateEnd: Date;
  localization: LocalizationDTOModel;

}
