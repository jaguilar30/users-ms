package com.bci.infra.api.router.facade;

import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.infra.api.router.controller.error.exception.UserException;
import com.bci.domain.entities.User;

public interface UsersFacade {

    UserDto createUser(User user) throws UserException;
}
