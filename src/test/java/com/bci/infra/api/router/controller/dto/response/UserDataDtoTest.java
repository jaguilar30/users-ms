package com.bci.infra.api.router.controller.dto.response;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class UserDataDtoTest {

    @Test
    void testGettersAndSetters() {
        UserDataDto user = new UserDataDto();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setName("Pedro");
        user.setEmail("pedro@email.com");
        user.setPassword("pass");
        PhonesDto phone = new PhonesDto();
        user.setPhones(List.of(phone));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreateAt(now);
        user.setUpdateAt(now);
        user.setLastLogin(now);
        user.setToken("token123");
        user.setActive(true);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Pedro");
        assertThat(user.getEmail()).isEqualTo("pedro@email.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.getPhones()).contains(phone);
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token123");
        assertThat(user.isActive()).isTrue();
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        PhonesDto phone = new PhonesDto();
        List<PhonesDto> phones = List.of(phone);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        UserDataDto user = new UserDataDto(id, "Ana", "ana@email.com", "pass2", phones, now, now, now, "token456", false);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Ana");
        assertThat(user.getEmail()).isEqualTo("ana@email.com");
        assertThat(user.getPassword()).isEqualTo("pass2");
        assertThat(user.getPhones()).isEqualTo(phones);
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token456");
        assertThat(user.isActive()).isFalse();
    }

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        PhonesDto phone = new PhonesDto();
        List<PhonesDto> phones = List.of(phone);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        UserDataDto user = UserDataDto.builder()
                .id(id)
                .name("Luis")
                .email("luis@email.com")
                .password("pass3")
                .phones(phones)
                .createAt(now)
                .updateAt(now)
                .lastLogin(now)
                .token("token789")
                .active(true)
                .build();

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Luis");
        assertThat(user.getEmail()).isEqualTo("luis@email.com");
        assertThat(user.getPassword()).isEqualTo("pass3");
        assertThat(user.getPhones()).isEqualTo(phones);
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token789");
        assertThat(user.isActive()).isTrue();
    }
}
