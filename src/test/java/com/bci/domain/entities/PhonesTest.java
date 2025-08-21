package com.bci.domain.entities;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class PhonesTest {

    @Test
    void testGettersAndSetters() {
        Phones phone = new Phones();
        UUID id = UUID.randomUUID();
        phone.setId(id);
        phone.setNumber("123456789");
        phone.setCityCode("01");
        phone.setCountryCode("57");

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("123456789");
        assertThat(phone.getCityCode()).isEqualTo("01");
        assertThat(phone.getCountryCode()).isEqualTo("57");
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Phones phone = new Phones(id, "987654321", "02", "58");

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("987654321");
        assertThat(phone.getCityCode()).isEqualTo("02");
        assertThat(phone.getCountryCode()).isEqualTo("58");
    }

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        Phones phone = Phones.builder()
                .id(id)
                .number("5555555")
                .cityCode("03")
                .countryCode("59")
                .build();

        assertThat(phone.getId()).isEqualTo(id);
        assertThat(phone.getNumber()).isEqualTo("5555555");
        assertThat(phone.getCityCode()).isEqualTo("03");
        assertThat(phone.getCountryCode()).isEqualTo("59");
    }
}