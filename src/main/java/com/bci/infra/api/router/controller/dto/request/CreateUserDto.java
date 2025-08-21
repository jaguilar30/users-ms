package com.bci.infra.api.router.controller.dto.request;

import com.bci.infra.api.router.controller.dto.response.PhonesDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private List<PhonesDto> phones;
}
