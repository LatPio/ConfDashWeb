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
public class BookingRequest {
    Integer eventIDData;
    LocalDateTime dateStart;
    LocalizationDTO localization;
    Integer eventTime;
    boolean locationConflict;
    boolean timeConflict;
}
