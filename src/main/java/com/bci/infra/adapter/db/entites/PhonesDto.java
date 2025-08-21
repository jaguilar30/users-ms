package com.bci.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phones")
public class PhonesDto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number", length = 100)
    private String number;

    @Column(name = "city_code", length = 50)
    private String cityCode;

    @Column(name = "country_code", length = 50)
    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UsersDto user;
}
