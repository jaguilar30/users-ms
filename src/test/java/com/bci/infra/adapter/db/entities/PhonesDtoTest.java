package com.bci.infra.adapter.db.entities;

import com.bci.infra.adapter.db.entites.PhonesDto;
import com.bci.infra.adapter.db.entites.UsersDto;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class PhonesDtoTest {

    @Test
    void testGettersAndSetters() {
        PhonesDto phone = new PhonesDto();
        UUID id = UUID.randomUUID();
        phone.setId(id);
        phone.setNumber("123456789");
        phone.setCityCode("01");
        phone.setCountryCode("57");
        UsersDto user = new UsersDto();
        phone.setUser(user);

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("123456789");
        assertThat(phone.getCityCode()).isEqualTo("01");
        assertThat(phone.getCountryCode()).isEqualTo("57");
        assertThat(phone.getUser()).isEqualTo(user);
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UsersDto user = new UsersDto();
        PhonesDto phone = new PhonesDto(id, "987654321", "02", "58", user);

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("987654321");
        assertThat(phone.getCityCode()).isEqualTo("02");
        assertThat(phone.getCountryCode()).isEqualTo("58");
        assertThat(phone.getUser()).isEqualTo(user);
    }

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        UsersDto user = new UsersDto();
        PhonesDto phone = PhonesDto.builder()
                .id(id)
                .number("5555555")
                .cityCode("03")
                .countryCode("59")
                .user(user)
                .build();

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("5555555");
        assertThat(phone.getCityCode()).isEqualTo("03");
        assertThat(phone.getCountryCode()).isEqualTo("59");
        assertThat(phone.getUser()).isEqualTo(user);
    }
}