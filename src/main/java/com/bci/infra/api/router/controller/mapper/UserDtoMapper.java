package com.bci.infra.api.router.controller.mapper;

import com.bci.domain.entities.Phones;
import com.bci.infra.api.router.controller.dto.request.CreateUserDto;
import com.bci.domain.entities.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    static User toEntity(CreateUserDto createUserDto){
        if(Objects.isNull(createUserDto)){
            return User.builder().build();
        }

        List<Phones> phones = new ArrayList<>();
        if (!createUserDto.getPhones().isEmpty()) {
            phones = createUserDto.getPhones().stream().map(p -> Phones.builder()
                    .countryCode(p.getCountryCode())
                    .number(p.getNumber())
                    .cityCode(p.getCityCode()).build()).toList();
        }

        return User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .phones(phones).build();
    }
}
