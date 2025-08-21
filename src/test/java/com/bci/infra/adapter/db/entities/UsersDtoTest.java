package com.bci.infra.adapter.db.entities;

import com.bci.infra.adapter.db.entites.PhonesDto;
import com.bci.infra.adapter.db.entites.UsersDto;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class UsersDtoTest {

    @Test
    void testGettersAndSetters() {
        UsersDto user = new UsersDto();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setName("Juan");
        user.setEmail("juan@email.com");
        user.setPassword("pass");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreateAt(now);
        user.setUpdateAt(now);
        user.setLastLogin(now);
        user.setToken("token123");
        user.setActive(true);
        PhonesDto phone = new PhonesDto();
        user.setPhones(List.of(phone));

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Juan");
        assertThat(user.getEmail()).isEqualTo("juan@email.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token123");
        assertThat(user.isActive()).isTrue();
        assertThat(user.getPhones()).contains(phone);
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        PhonesDto phone = new PhonesDto();
        List<PhonesDto> phones = List.of(phone);
        UsersDto user = new UsersDto(id, "Ana", "ana@email.com", "pass2", now, now, now, "token456", false, phones);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Ana");
        assertThat(user.getEmail()).isEqualTo("ana@email.com");
        assertThat(user.getPassword()).isEqualTo("pass2");
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token456");
        assertThat(user.isActive()).isFalse();
        assertThat(user.getPhones()).isEqualTo(phones);
    }

    @Test
    void testBuilder() {
        UUID id = UUID.randomUUID();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        PhonesDto phone = new PhonesDto();
        List<PhonesDto> phones = List.of(phone);
        UsersDto user = UsersDto.builder()
                .id(id)
                .name("Luis")
                .email("luis@email.com")
                .password("pass3")
                .createAt(now)
                .updateAt(now)
                .lastLogin(now)
                .token("token789")
                .active(true)
                .phones(phones)
                .build();

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Luis");
        assertThat(user.getEmail()).isEqualTo("luis@email.com");
        assertThat(user.getPassword()).isEqualTo("pass3");
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token789");
        assertThat(user.isActive()).isTrue();
        assertThat(user.getPhones()).isEqualTo(phones);
    }
}
