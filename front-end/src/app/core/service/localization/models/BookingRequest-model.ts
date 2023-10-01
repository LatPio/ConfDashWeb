import {LocalizationDTOModel} from "./LocalizationDTOModel";

export class BookingRequestModel{
  eventIDDat: number;
  dateStart: Date;
  localization: LocalizationDTOModel;
  eventTime: number;
  locationConflict: boolean;
  timeConflict: boolean;
}
