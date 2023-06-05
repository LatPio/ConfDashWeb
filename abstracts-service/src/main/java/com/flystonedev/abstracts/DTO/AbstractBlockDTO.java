package com.flystonedev.abstracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractBlockDTO {
    private Integer id;
    private boolean accepted;
}
