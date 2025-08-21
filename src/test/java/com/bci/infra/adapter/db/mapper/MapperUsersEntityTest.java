package com.bci.infra.adapter.db.mapper;

import com.bci.domain.entities.User;
import com.bci.infra.adapter.db.entites.PhonesDto;
import com.bci.infra.adapter.db.entites.UsersDto;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class MapperUsersEntityTest {

    @Test
    void testToUserWithNullUser() {
        User user = MapperUsersEntity.toUser(null);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
    }

    @Test
    void testToUserWithoutPhones() {
        UUID id = UUID.randomUUID();
        UsersDto usersDto = UsersDto.builder()
                .id(id)
                .name("Test")
                .email("test@email.com")
                .password("1234")
                .active(true)
                .createAt(new Timestamp(System.currentTimeMillis()))
                .updateAt(new Timestamp(System.currentTimeMillis()))
                .lastLogin(new Timestamp(System.currentTimeMillis()))
                .token("token")
                .phones(null)
                .build();

        User user = MapperUsersEntity.toUser(usersDto);
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPhones()).isEmpty();
    }

    @Test
    void testToUserWithPhones() {
        UUID id = UUID.randomUUID();
        PhonesDto phoneDto = PhonesDto.builder()
                .id(UUID.randomUUID())
                .number("123456")
                .cityCode("01")
                .countryCode("57")
                .build();

        UsersDto usersDto = UsersDto.builder()
                .id(id)
                .name("Test")
                .email("test@email.com")
                .password("1234")
                .active(true)
                .createAt(new Timestamp(System.currentTimeMillis()))
                .updateAt(new Timestamp(System.currentTimeMillis()))
                .lastLogin(new Timestamp(System.currentTimeMillis()))
                .token("token")
                .phones(List.of(phoneDto))
                .build();

        User user = MapperUsersEntity.toUser(usersDto);
        assertThat(user.getPhones()).hasSize(1);
        assertThat(user.getPhones().get(0).getNumber()).isEqualTo("123456");
        assertThat(user.getPhones().get(0).getCityCode()).isEqualTo("01");
        assertThat(user.getPhones().get(0).getCountryCode()).isEqualTo("57");
    }
}
