package com.bci.domain.entities;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setName("Juan");
        user.setEmail("juan@email.com");
        user.setPassword("pass");
        user.setPhones(List.of(new Phones()));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreateAt(now);
        user.setUpdateAt(now);
        user.setLastLogin(now);
        user.setToken("token123");
        user.setActive(true);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("Juan");
        assertThat(user.getEmail()).isEqualTo("juan@email.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.getPhones()).isNotEmpty();
        assertThat(user.getCreateAt()).isEqualTo(now);
        assertThat(user.getUpdateAt()).isEqualTo(now);
        assertThat(user.getLastLogin()).isEqualTo(now);
        assertThat(user.getToken()).isEqualTo("token123");
        assertThat(user.isActive()).isTrue();
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<Phones> phones = List.of(new Phones());
        User user = new User(id, "Ana", "ana@email.com", "pass2", phones, now, now, now, "token456", false);

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
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<Phones> phones = List.of(new Phones());
        User user = User.builder()
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
