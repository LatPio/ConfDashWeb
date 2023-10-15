package com.flystonedev.event.DTO;

public record StatisticResponse(
        Long eventsCount,
        Long eventTypeCounts
) {
}
