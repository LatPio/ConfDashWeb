package com.flystonedev.basket.service;

import com.flystonedev.basket.DTO.BasketDTO;
import com.flystonedev.basket.DTO.BasketManyRequest;
import com.flystonedev.basket.config.JwtConverter;
import com.flystonedev.basket.exeption.EntityNotFoundException;
import com.flystonedev.basket.exeption.BookingEditionBlockedException;
import com.flystonedev.basket.mapper.BasketItemMapper;
import com.flystonedev.basket.model.BasketItem;
import com.flystonedev.basket.repository.BasketRepository;
import com.flystonedev.event.DTO.EventEntityDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final JwtConverter jwtConverter;
    private final BasketItemMapper basketMapper = Mappers.getMapper(BasketItemMapper.class);

    /*
     *
     * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
     *
     * */

    public BasketDTO bookUSerEvent(EventEntityDTO eventEntityDTO){
        BasketItem basketItem = BasketItem.builder()
                .name(eventEntityDTO.getName())
                .eventId(eventEntityDTO.getId())
                .authId(jwtConverter.getKeycloakUserID())
                .deletable(true)
                .build();
        basketRepository.save(basketItem);
        return basketMapper.map(basketItem);
    }

    public List<BasketDTO> basketUserListOwnedItems(){
        List<BasketItem> basketDTOListForUser = basketRepository.findBasketItemByAuthId(jwtConverter.getKeycloakUserID());
        return  basketDTOListForUser.stream().map(basketMapper::map).collect(Collectors.toList());
    }
    public BasketDTO getBooking(Integer id){
        return basketRepository.findBasketItemByIdAndAuthId(id, jwtConverter.getKeycloakUserID()).map(basketMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteBooking(Integer id) {
        BasketDTO exist = getBooking(id);
        if (exist == null) {
            throw new EntityNotFoundException();
        } else
        if (!exist.getDeletable()) {
            throw new BookingEditionBlockedException();
        } else {
            basketRepository.deleteBasketItemByIdAndAuthId(id, jwtConverter.getKeycloakUserID().toString());
        }

    }

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */

    public BasketDTO bookAdminEvent(BasketDTO basketDTO){
        BasketItem basketItem = BasketItem.builder()
                .name(basketDTO.getName())
                .eventId(basketDTO.getEventId())
                .authId(basketDTO.getAuthId())
                .deletable(basketDTO.getDeletable())
                .build();
        basketRepository.save(basketItem);
        return basketMapper.map(basketItem);
    }

    public List<BasketDTO> bookAdminManyEvents(BasketManyRequest basketManyRequest){
        List<BasketDTO> savedList = new ArrayList<>();
        List<BasketItem> toDatabase = new ArrayList<>();
        for (int i = 0; i < basketManyRequest.authIds().size(); i++) {
            BasketItem basketItem = BasketItem.builder()
                    .name(basketManyRequest.name())
                    .eventId(basketManyRequest.eventId())
                    .deletable(basketManyRequest.deletable())
                    .authId(basketManyRequest.authIds().get(i))
                    .build();
//            basketRepository.save(basketItem);
            savedList.add(basketMapper.map(basketItem));
            toDatabase.add(basketItem);
        }
        basketRepository.saveAll(toDatabase);

        return savedList;
    }

    public List<BasketDTO> basketAdminListOwnedItems(String authId){
        List<BasketItem> basketDTOListForUser = basketRepository.findBasketItemByAuthId(authId);
        return  basketDTOListForUser.stream().map(basketMapper::map).collect(Collectors.toList());
    }
    public List<BasketDTO> basketAdminListAllItems(){
        List<BasketItem> basketDTOListForUser = basketRepository.findAll();
        return  basketDTOListForUser.stream().map(basketMapper::map).collect(Collectors.toList());
    }
    public BasketDTO getAdminBooking(Integer id, String authId){
        return basketRepository.findBasketItemByIdAndAuthId(id, authId).map(basketMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteAdminBooking(Integer id, String authId) {
        BasketDTO exist = getAdminBooking(id, authId);
        if (exist == null) {
            throw new EntityNotFoundException();
        }  else {
            basketRepository.deleteById(id);
        }
    }



}
