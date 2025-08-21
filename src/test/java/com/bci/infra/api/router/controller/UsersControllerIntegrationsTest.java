package com.bci.infra.api.router.controller;

import com.bci.UsersApplication;
import com.bci.app.UsersService;
import com.bci.infra.adapter.db.UsersRepository;
import com.bci.infra.api.router.controller.dto.response.UserDataDto;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UsersApplication.class })
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UsersControllerIntegrationsTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    private MockMvc mockMvc;

    private String body;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.body = "{\n" +
                "    \"name\": \"Juan Rodriguez\",\n" +
                "    \"email\": \"juan@gmail.cl\",\n" +
                "    \"password\": \"Syc@juluaga2016\",\n" +
                "    \"phones\": [\n" +
                "        {\n" +
                "            \"number\": \"1234567\",\n" +
                "            \"city_code\": \"1\",\n" +
                "            \"country_code\": \"57\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    @Test
    public void createUserThenVerifyResponse() throws Exception {

        Mockito.when(usersService.createUser(any())).thenReturn(getUserDto());


        this.mockMvc.perform(post("/users" ).content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201)).andExpect(content()
                        .contentType("application/json"))
                .andReturn();

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
}
