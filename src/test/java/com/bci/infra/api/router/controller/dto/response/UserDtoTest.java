package com.bci.infra.api.router.controller.dto.response;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    void testGettersAndSetters() {
        UserDto userDto = new UserDto();
        UserDataDto data = new UserDataDto();
        userDto.setData(data);

        assertThat(userDto.getData()).isEqualTo(data);
    }

    @Test
    void testAllArgsConstructor() {
        UserDataDto data = new UserDataDto();
        UserDto userDto = new UserDto(data);

        assertThat(userDto.getData()).isEqualTo(data);
    }

    @Test
    void testBuilder() {
        UserDataDto data = new UserDataDto();
        UserDto userDto = UserDto.builder()
                .data(data)
                .build();

        assertThat(userDto.getData()).isEqualTo(data);
    }
}
