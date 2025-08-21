package com.bci.infra.api.router.controller.dto.response;

import com.bci.infra.api.router.controller.dto.GenericResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends GenericResponseDTO {
    private UserDataDto data;
}