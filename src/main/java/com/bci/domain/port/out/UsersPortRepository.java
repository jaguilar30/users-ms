package com.bci.domain.port.out;

import com.bci.domain.entities.User;
import com.bci.infra.adapter.db.entites.UsersDto;

public interface UsersPortRepository {
    User save(UsersDto user);
    User getUserByEmail(String email);
}
