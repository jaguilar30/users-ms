package com.bci.app;

import com.bci.UsersApplication;
import com.bci.domain.entities.Phones;
import com.bci.domain.entities.User;
import com.bci.domain.port.out.UsersPortRepository;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.infra.api.router.controller.error.exception.UserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersPortRepository usersPortRepository;

    @Test
    public void createUserTestWhenSuccess() throws UserException {
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        UserDto response = usersService.createUser(getUser());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getData().getName(), getUser().getName());
    }

    @Test
    public void createUserTestWhenDataAccessException() {
        Mockito.when(usersPortRepository.save(any())).thenThrow(new RecoverableDataAccessException("jpa error"));
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(getUser()));
    }

    @Test
    public void createUserTestWhenExists() {
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(getUser());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(getUser()));
    }

    @Test
    public void createUserTestWhenNotEmail() {
        User user = getUser();
        user.setEmail("");
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(user));
    }

    @Test
    public void createUserTestWhenNotName() {
        User user = getUser();
        user.setName("");
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(user));
    }

    @Test
    public void createUserTestWhenEmailNotMatch() {
        User user = getUser();
        user.setEmail("juan@mail.com");
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(user));
    }

    @Test
    public void createUserTestWhenPasswordNotMatch() {
        User user = getUser();
        user.setPassword("123456");
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByEmail(any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(user));
    }

    private User getUser() {
        return User.builder()
                .name("name")
                .active(Boolean.TRUE)
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .lastLogin(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .id(UUID.randomUUID())
                .email("juan@mail.cl")
                .password("Syc@juluaga2016")
                .phones(Collections.singletonList(Phones.builder()
                        .cityCode("code")
                        .id(UUID.randomUUID())
                        .countryCode("code")
                        .number("30291823018").build()))
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvMTIzIiwiaWF0IjoxNzU1Nzg4MTE2LCJleHAiOjE3NTU3OTUzMTZ9.OWjChsRnYSjGqcCwiEIseb--qbzZMWUJcFP6zyMaHNA")
                .build();
    }
}
