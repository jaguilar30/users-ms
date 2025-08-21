package com.bci.infra.api.router.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.bci.infra.api.router.controller.error.ErrorConsts.MOCK_MENSAJE_ERROR;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDTO {
    @Schema(example = MOCK_MENSAJE_ERROR)
    private String message;
}
