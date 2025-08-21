package com.bci.infra.api.router.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhonesDto {
    private String number;
    @JsonProperty("city_code")
    private String cityCode;
    @JsonProperty("country_code")
    private String countryCode;
}