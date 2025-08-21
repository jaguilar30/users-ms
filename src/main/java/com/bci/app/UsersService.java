package com.bci.app;

import com.bci.app.mapper.UserMapper;
import com.bci.domain.entities.User;
import com.bci.domain.port.out.UsersPortRepository;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.infra.api.router.controller.error.exception.UserException;
import com.bci.infra.api.router.facade.UsersFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bci.app.ServiceConsts.*;

@Slf4j
@Service
public class UsersService implements UsersFacade {

    @Autowired
    private UsersPortRepository usersPortRepository;

    @Value("${validate.data.email}")
    private String regexEmail;

    @Value("${validate.data.password}")
    private String regexPassword;

    @Value("${jwt.token.expired}")
    private String expired;

    public UserDto createUser(User userToSave) throws UserException {
        try{
            validUserBeforeCreate(userToSave);

            validateData(userToSave);

            User user = usersPortRepository.save(UserMapper.toUsersEntityDto(userToSave, Integer.parseInt(expired)));

            log.info(String.format(MSG_PROCESS_SERVICE, "created", "email: ", userToSave.getEmail()));
            return UserMapper.toUserDto(UserMapper.toDto(user), MSG_USER_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "created",  "email: ", userToSave.getEmail(),
                    ex.getMessage()));
            throw new UserException("422-1", ex.getMessage());
        }
    }

    private void validUserBeforeCreate(User userToCreate) throws UserException {
        User user = usersPortRepository.getUserByEmail(
                userToCreate.getEmail());

        if(!Objects.isNull(user.getId())) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "email: ", userToCreate.getEmail(),
                    EXISTS_USER));
                throw new UserException("422-2", EXISTS_USER);
        }
    }

    private void validateData(User userToCreate) throws UserException {
        if(Objects.isNull(userToCreate.getName()) || userToCreate.getName().isBlank()){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "name is empty",  "name: ", userToCreate.getName(),
                    ERROR_NAME_EMPTY));
            throw new UserException("422-3", ERROR_NAME_EMPTY);
        }

        if(Objects.isNull(userToCreate.getEmail()) || userToCreate.getEmail().isEmpty()){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "email is empty",  "email: ", userToCreate.getEmail(),
                    ERROR_EMAIL_EMPTY));
            throw new UserException("422-4", ERROR_EMAIL_EMPTY);
        }

        if(!userToCreate.getEmail().matches(regexEmail)){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "email format",  "email: ", userToCreate.getEmail(),
                    "Email format not allowed."));
            throw new UserException("422-5", ERROR_FORMAT_EMAIL);
        }

        if(!userToCreate.getPassword().matches(regexPassword)){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "password format",  "password: ", userToCreate.getPassword(),
                    "Password format not allowed."));
            throw new UserException("422-6", ERROR_FORMAT_PASSWORD);
        }
    }
}
