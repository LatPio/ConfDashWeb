package com.flystonedev.site.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SiteDTO {
    private Integer id;
    private String name;
    private String body;
    private Integer orderNumber;
}
