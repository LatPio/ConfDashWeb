package com.flystonedev.localization.DTO;

public record StatsLocationResponse(
        Long mapCount,
        Long roomsCount,
        Long bookingCount
) {
}
