package com.bci.infra.adapter.db;

import com.bci.UsersApplication;
import com.bci.domain.entities.User;
import com.bci.infra.adapter.db.entites.PhonesDto;
import com.bci.infra.adapter.db.entites.UsersDto;
import com.bci.infra.adapter.db.jpa.UsersJpaRepository;
import com.bci.infra.api.router.controller.error.exception.UserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @MockBean
    private UsersJpaRepository usersJpaRepository;

    @Test
    public void saveTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.save(any())).thenReturn(getUsersDto());

        User response = usersRepository.save(getUsersDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getName(), getUsersDto().getName());
    }

    @Test
    public void getUserByEmailTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.findByEmail(any())).thenReturn(getUsersDto());

        User response = usersRepository.getUserByEmail(getUsersDto().getEmail());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getName(), getUsersDto().getName());
    }

    private UsersDto getUsersDto() {
        return UsersDto.builder()
                .name("name")
                .id(UUID.randomUUID())
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .email("juan@mail.cl")
                .active(Boolean.TRUE)
                .lastLogin(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvMTIzIiwiaWF0IjoxNzU1Nzg4MTE2LCJleHAiOjE3NTU3OTUzMTZ9.OWjChsRnYSjGqcCwiEIseb--qbzZMWUJcFP6zyMaHNA")
                .phones(Collections.singletonList(PhonesDto.builder()
                        .countryCode("code")
                        .id(UUID.randomUUID())
                        .number("3192982")
                        .cityCode("code")
                        .user(UsersDto.builder().id(UUID.randomUUID()).build()).build())).build();
    }
}
