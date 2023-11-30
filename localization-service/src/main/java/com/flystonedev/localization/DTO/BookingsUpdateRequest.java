package com.flystonedev.localization.DTO;

import java.time.LocalDateTime;

public record BookingsUpdateRequest(
        Integer id,
        Integer eventIDData,
        boolean locationConflict,
        boolean timeConflict,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocalizationWithOutMapDTO localization
) {
}
