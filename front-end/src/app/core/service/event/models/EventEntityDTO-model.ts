import {EventTypeDTOModel} from "./EventTypeDTO-model";

export class EventEntityDTOModel{
  id: number;
  name: string;
  abstractName: string;
  abstractId: string;
  localizationId: string;
  localizationName: string;
  eventType: EventTypeDTOModel;
  dateTimeOfEvent: Date;

}
