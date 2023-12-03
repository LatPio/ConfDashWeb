package com.flystonedev.event.service;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.StatisticResponse;
import com.flystonedev.event.clients.AbstractClient;
import com.flystonedev.event.clients.LocalizationClient;
import com.flystonedev.event.exeption.EntityNotFoundException;
import com.flystonedev.event.exeption.config.GlobalErrorCode;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.model.EventType;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.event.repository.EventTypeRepository;
import com.flystonedev.localization.DTO.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventEntityService {

    private final EventEntityRepository eventEntityRepository;

    private final EventTypeRepository eventTypeRepository;
    private final EventEntityMapper eventEntityMapper = Mappers.getMapper(EventEntityMapper.class);

    private final EventTypeMapper eventTypeMapper = Mappers.getMapper(EventTypeMapper.class);


    private AbstractClient abstractClient;
    private LocalizationClient localizationClient;
    @Transactional
    public EventEntityDTO createEventEntity(EventEntityDTO eventEntityDTO){

        AbstractOutResponse abstractSelected = abstractClient.abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId()));
        LocalizationOutResponse localizationSelected =  localizationClient.localizationOutResponse(Integer.valueOf(eventEntityDTO.getLocalizationId()));
        EventType selectedEventType = eventTypeRepository.getReferenceById(eventEntityDTO.getEventType().getId());

        EventEntity eventEntity = EventEntity.builder()
                .name(eventEntityDTO.getName())
                .ownerId(abstractSelected.getOwnerId())
                .abstractName(abstractSelected.getAbstractTitle())
                .abstractId(eventEntityDTO.getAbstractId())
                .localizationId(eventEntityDTO.getLocalizationId())
                .localizationName(localizationSelected.getRoom())
                .eventType(selectedEventType)
                .startOfEvent(eventEntityDTO.getStartOfEvent())
                .endOfEvent(eventEntityDTO.getStartOfEvent().plusMinutes(selectedEventType.getTimeInMinutes()))
                .build();
        EventEntity saved = eventEntityRepository.save(eventEntity);

        BookingRequest bookingRequest = BookingRequest.builder()
                    .eventIDData(saved.getId())
                    .dateStart(saved.getStartOfEvent())
                    .eventTime(saved.getEventType().getTimeInMinutes())
                    .localization(LocalizationDTO.builder().id(Integer.valueOf(saved.getLocalizationId())).build())
                    .timeConflict(saved.getEventType().isTimeConflict())
                    .locationConflict(saved.getEventType().isLocationConflict())
                    .build();
        BookingsDTO savedBooking = localizationClient.createBookingsDTO(bookingRequest);

        saved.setBookingId(savedBooking.getId());
        eventEntityRepository.save(saved);
        return eventEntityMapper.map(saved);


    }

    public List<EventEntityDTO> eventEntityDTOList() {
        List<EventEntity> eventEntityList = eventEntityRepository.findAll();
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public List<EventEntityDTO> eventEntityDTOuserList(List<Integer> listBookedEvents) {
        List<EventEntity> eventEntityList = eventEntityRepository.findByIdIn(listBookedEvents);
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public List<EventEntityDTO> eventEntityDTOListByLocalization(String id) {
        List<EventEntity> eventEntityList = eventEntityRepository.findEventEntitiesByLocalizationId(id);
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public EventEntityDTO get(Integer id){
        return eventEntityRepository.findById(id).map(eventEntityMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public EventEntityDTO update(EventEntityDTO eventEntityDTO){
        EventEntityDTO exist = get(eventEntityDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException("Event With provided ID dont exist in database", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);
        }
        AbstractOutResponse abstractSelected = abstractClient.abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId()));
        LocalizationOutResponse localizationSelected =  localizationClient.localizationOutResponse(Integer.valueOf(eventEntityDTO.getLocalizationId()));
        EventType selectedEventType = eventTypeRepository.getReferenceById(eventEntityDTO.getEventType().getId());

        EventEntity eventEntity = EventEntity.builder()
                .id(exist.getId())
                .name(eventEntityDTO.getName())
                .ownerId(abstractSelected.getOwnerId())
                .abstractName(abstractSelected.getAbstractTitle())
                .abstractId(eventEntityDTO.getAbstractId())
                .localizationId(eventEntityDTO.getLocalizationId())
                .localizationName(localizationSelected.getRoom())
                .eventType(selectedEventType)
                .startOfEvent(eventEntityDTO.getStartOfEvent())
                .endOfEvent(eventEntityDTO.getStartOfEvent().plusMinutes(selectedEventType.getTimeInMinutes()))
                .bookingId(exist.getBookingId())
                .build();

        BookingsDTOLight existBooking = localizationClient.getBookingsDTO(exist.getBookingId());
        BookingsUpdateRequest bookingToUpdate = new BookingsUpdateRequest(
                existBooking.getId(),
                existBooking.getEventIDData(),
                existBooking.isLocationConflict(),
                existBooking.isTimeConflict(),
                eventEntity.getStartOfEvent(),
                eventEntity.getEndOfEvent(),
                LocalizationWithOutMapDTO.builder()
                        .id(existBooking.getLocalization().getId())
                        .room(existBooking.getLocalization().getRoom())
                        .coordinateX(existBooking.getLocalization().getCoordinateX())
                        .coordinateY(existBooking.getLocalization().getCoordinateY())
                        .build()
        );
        BookingsDTOLight updatedBooking = localizationClient.updateBookingsDTO(bookingToUpdate);

        EventEntity updated = eventEntityRepository.save(eventEntity);

        return eventEntityMapper.map(updated);


    }

    public void delete(Integer id){
        eventEntityRepository.deleteById(id);
    }

    public StatisticResponse statistic(){
        return new StatisticResponse(eventEntityRepository.count(), eventTypeRepository.count());

    }
}
