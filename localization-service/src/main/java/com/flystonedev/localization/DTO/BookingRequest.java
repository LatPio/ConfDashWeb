package com.flystonedev.localization.DTO;

import com.flystonedev.localization.model.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.events.Event;

import java.time.Duration;
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
