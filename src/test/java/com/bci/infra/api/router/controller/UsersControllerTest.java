package com.bci.infra.api.router.controller;

import com.bci.UsersApplication;
import com.bci.app.UsersService;
import com.bci.infra.api.router.controller.dto.request.CreateUserDto;
import com.bci.infra.api.router.controller.dto.response.UserDataDto;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.infra.api.router.controller.error.exception.UserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersControllerTest {

    @Autowired
    private UsersController usersController;

    @MockBean
    private UsersService usersService;

    @Test
    public void createUserTestWhenSuccess() throws UserException {
        Mockito.when(usersService.createUser(any())).thenReturn(getUserDto());

        ResponseEntity<UserDto> response = usersController.createUser(getCreateAndUpdateUserDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().getName(),
                getUserDto().getData().getName());
    }

    @Test
    public void createUserTestWhenUserException() throws UserException {
        Mockito.when(usersService.createUser(any())).thenThrow(new UserException("422-1", "error"));

        Assertions.assertThrows(UserException.class, () -> usersController.createUser(getCreateAndUpdateUserDto()));
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .data(UserDataDto.builder()
                        .name("Name")
                        .id(UUID.randomUUID())
                        .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                        .createAt(Timestamp.valueOf(LocalDateTime.now()))
                        .email("juan@mail.cl")
                        .active(Boolean.TRUE)
                        .lastLogin(Timestamp.valueOf(LocalDateTime.now()))
                        .password("Syc@juluaga2016")
                        .token("token").build())
                .build();
    }

    CreateUserDto getCreateAndUpdateUserDto(){
        return CreateUserDto.builder()
                .email("juan@mail.cl")
                .password("Syc@juluaga2016")
                .name("name")
                .build();
    }
}
