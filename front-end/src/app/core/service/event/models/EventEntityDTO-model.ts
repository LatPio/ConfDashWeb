import {EventTypeDTOModel} from "./EventTypeDTO-model";

export class EventEntityDTOModel{
  id: number;
  name: string;
  ownerId: number;
  abstractName: string;
  abstractId: number;
  localizationId: number;
  localizationName: string;
  eventType: EventTypeDTOModel;
  dateTimeOfEvent: Date;
  bookingId: number;

}
