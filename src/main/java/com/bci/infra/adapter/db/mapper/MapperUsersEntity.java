package com.bci.infra.adapter.db.mapper;

import com.bci.domain.entities.Phones;
import com.bci.domain.entities.User;
import com.bci.infra.adapter.db.entites.UsersDto;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring")
public interface MapperUsersEntity {

    static User toUser(UsersDto user){
        if (Objects.isNull(user)) {
            return User.builder().build();
        }

        List<Phones> phones = new ArrayList<>();

        if(!Objects.isNull(user.getPhones()) && !user.getPhones().isEmpty()){
            phones = user.getPhones().stream().map(p -> Phones.builder()
                    .number(p.getNumber())
                    .id(p.getId())
                    .countryCode(p.getCountryCode())
                    .cityCode(p.getCityCode()).build()).toList();
        }


        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(user.getToken())
                .password(user.getPassword())
                .active(user.isActive())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .lastLogin(user.getLastLogin())
                .phones(phones).build();
    }
}
