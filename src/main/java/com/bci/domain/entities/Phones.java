package com.bci.domain.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phones {
    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;
}