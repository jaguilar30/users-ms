package com.bci.infra.api.router.controller.mapper;

import com.bci.domain.entities.Phones;
import com.bci.domain.entities.User;
import com.bci.infra.api.router.controller.dto.request.CreateUserDto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class UserDtoMapperTest {

    @Test
    void testToEntityWithNullDto() {
        User user = UserDtoMapper.toEntity(null);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isNull();
    }

    @Test
    void testToEntityWithoutPhones() {
        CreateUserDto dto = CreateUserDto.builder()
                .name("Carlos")
                .email("carlos@email.com")
                .password("pass123")
                .phones(null)
                .build();

        User user = UserDtoMapper.toEntity(dto);
        assertThat(user.getName()).isEqualTo("Carlos");
        assertThat(user.getEmail()).isEqualTo("carlos@email.com");
        assertThat(user.getPassword()).isEqualTo("pass123");
        assertThat(user.getPhones()).isEmpty();
    }

    @Test
    void testToEntityWithPhones() {
        com.bci.infra.api.router.controller.dto.response.PhonesDto phoneDto = com.bci.infra.api.router.controller.dto.response.PhonesDto.builder()
                .countryCode("57")
                .cityCode("01")
                .number("123456789")
                .build();

        CreateUserDto dto = CreateUserDto.builder()
                .name("Ana")
                .email("ana@email.com")
                .password("pass456")
                .phones(List.of(phoneDto))
                .build();

        User user = UserDtoMapper.toEntity(dto);
        assertThat(user.getPhones()).hasSize(1);
        Phones phone = user.getPhones().get(0);
        assertThat(phone.getCountryCode()).isEqualTo("57");
        assertThat(phone.getCityCode()).isEqualTo("01");
        assertThat(phone.getNumber()).isEqualTo("123456789");
    }
}
