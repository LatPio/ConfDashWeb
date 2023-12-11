package com.flystonedev.basket.service;

import com.flystonedev.basket.DTO.BasketDTO;
import com.flystonedev.basket.DTO.EventDTOs.EventEntityDTO;
import com.flystonedev.basket.SampleData;
import com.flystonedev.basket.config.JwtConverter;
import com.flystonedev.basket.mapper.BasketItemMapper;
import com.flystonedev.basket.model.BasketItem;
import com.flystonedev.basket.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest implements SampleData {

    @InjectMocks
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private JwtConverter jwtConverter;
    private final BasketItemMapper basketItemMapper = Mappers.getMapper(BasketItemMapper.class);

    @Test
    void bookUSerEvent() {
        //given
        EventEntityDTO data = getSampleInitialDataForBooking();
        BasketDTO expected = getSampleOfBasketDTO();
        expected.setId(null);
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when
        basketService.bookUSerEvent(data);
        //then
        ArgumentCaptor<BasketItem> basketItemArgumentCaptor = ArgumentCaptor.forClass(BasketItem.class);
        verify(basketRepository).save(basketItemArgumentCaptor.capture());

        BasketItem abstractsEntityArgumentCaptorValue = basketItemArgumentCaptor.getValue();
        assertThat(abstractsEntityArgumentCaptorValue).isEqualTo(basketItemMapper.map(expected));
    }

    @Test
    void basketUserListOwnedItems() {
        //given
        BasketDTO expected = getSampleOfBasketDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when
        basketService.basketUserListOwnedItems();
        //then
        verify(basketRepository, times(1)).findBasketItemByAuthId(expected.getAuthId());
        verifyNoMoreInteractions(basketRepository);
    }

    @Test
    void getBooking() {
        //given
        BasketItem expected = getSampleOfBasketItem();
        when(basketRepository.findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when
        BasketDTO actual = basketService.getBooking(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(basketRepository, times(1)).findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId());
        verifyNoMoreInteractions(basketRepository);
    }

    @Test
    void deleteBooking() {
        //given
        var expected = getSampleOfBasketDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        when(basketRepository.findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId()))
                .thenReturn(Optional.ofNullable(basketItemMapper.map(expected)));
        //when
        basketService.deleteBooking(expected.getId());
        //then
        verify(basketRepository, times(1)).deleteBasketItemByIdAndAuthId(anyInt(),anyString());
        verifyNoMoreInteractions(basketRepository);
    }

    @Test
    void bookAdminEvent() {
        //given
        BasketDTO expected = getSampleOfBasketDTO();
        expected.setId(null);
//        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when
        basketService.bookAdminEvent(expected);
        //then
        ArgumentCaptor<BasketItem> basketItemArgumentCaptor = ArgumentCaptor.forClass(BasketItem.class);
        verify(basketRepository).save(basketItemArgumentCaptor.capture());

        BasketItem abstractsEntityArgumentCaptorValue = basketItemArgumentCaptor.getValue();
        assertThat(abstractsEntityArgumentCaptorValue).isEqualTo(basketItemMapper.map(expected));
    }

    @Test
    void basketAdminListOwnedItems() {
        //given
        BasketDTO expected = getSampleOfBasketDTO();
        //when
        basketService.basketAdminListOwnedItems(expected.getAuthId());
        //then
        verify(basketRepository, times(1)).findBasketItemByAuthId(expected.getAuthId());
        verifyNoMoreInteractions(basketRepository);
    }

    @Test
    void getAdminBooking() {
        //given
        BasketItem expected = getSampleOfBasketItem();
        when(basketRepository.findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        //when
        BasketDTO actual = basketService.getAdminBooking(expected.getId(),expected.getAuthId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(basketRepository, times(1)).findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId());
        verifyNoMoreInteractions(basketRepository);
    }

    @Test
    void deleteAdminBooking() {
        //given
        var expected = getSampleOfBasketDTO();
        when(basketRepository.findBasketItemByIdAndAuthId(expected.getId(), expected.getAuthId()))
                .thenReturn(Optional.ofNullable(basketItemMapper.map(expected)));
        //when
        basketService.deleteAdminBooking(expected.getId(),expected.getAuthId());
        //then
        verify(basketRepository, times(1)).deleteById(expected.getId());
        verifyNoMoreInteractions(basketRepository);
    }
}