package com.bci.app.mapper;

import com.bci.infra.adapter.db.entites.UsersDto;
import com.bci.infra.api.router.controller.dto.response.PhonesDto;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.domain.entities.User;
import com.bci.infra.api.router.controller.dto.response.UserDataDto;
import com.bci.utils.JwtUtil;
import org.mapstruct.Mapper;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserMapper {

    static UserDataDto toDto(User user){
        List<PhonesDto> phonesDto = new ArrayList<>();

        if(!Objects.isNull(user.getPhones()) && !user.getPhones().isEmpty()){
            phonesDto = user.getPhones().stream().map(p -> PhonesDto.builder()
                    .countryCode(p.getCountryCode())
                    .number(p.getNumber())
                    .cityCode(p.getCityCode()).build()).toList();
        }

        return UserDataDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .password(user.getPassword())
                .name(user.getName())
                .active(user.isActive())
                .phones(phonesDto)
                .lastLogin(user.getLastLogin())
                .token(user.getToken()).build();
    }

    static UserDto toUserDto(UserDataDto userData, String msg){
        UserDto userDto =  UserDto.builder().data(userData).build();
        userDto.setMessage(msg);

        return userDto;
    }

    static UsersDto toUsersEntityDto(User user, Integer expired) {
        UUID userId = UUID.randomUUID();
        List<com.bci.infra.adapter.db.entites.PhonesDto> phonesDto = new ArrayList<>();

        if(!Objects.isNull(user.getPhones()) && !user.getPhones().isEmpty()){
            phonesDto = user.getPhones().stream().map(p -> com.bci.infra.adapter.db.entites.PhonesDto.builder()
                    .id(UUID.randomUUID())
                    .countryCode(p.getCountryCode())
                    .number(p.getNumber())
                    .user(UsersDto.builder().id(userId).build())
                    .cityCode(p.getCityCode()).build()).toList();
        }

        return  UsersDto.builder()
                .id(userId)
                .active(Boolean.TRUE)
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .lastLogin(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .email(user.getEmail())
                .token(JwtUtil.getToken(user.getEmail(), expired))
                .phones(phonesDto)
                .name(user.getName())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())).build();
    }
}
