package com.flystonedev.localization.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingsDTOLight {

    private Integer id;
    private Integer eventIDData;
    private boolean locationConflict;
    private boolean timeConflict;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalizationWithOutMapDTO localization;
}
