package com.bci.infra.api.router.controller;

import com.bci.infra.api.router.controller.dto.GenericResponseDTO;
import com.bci.infra.api.router.controller.dto.request.CreateUserDto;
import com.bci.infra.api.router.controller.dto.response.UserDto;
import com.bci.infra.api.router.controller.error.exception.UserException;
import com.bci.infra.api.router.controller.mapper.UserDtoMapper;
import com.bci.infra.api.router.RouterConsts;
import com.bci.infra.api.router.facade.UsersFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.bci.infra.api.router.RouterConsts.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(RouterConsts.CROSS_ORIGIN)
@RestController
@RequestMapping(path = RouterConsts.CONTROLLER_PATH)
@Tag(name = RouterConsts.API)
public class UsersController {

    @Autowired
    private UsersFacade usersFacade;

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_CREATE_USER, description = RouterConsts.NOTE_API_OPERATION_CREATE_USER)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
                    content =  { @Content( schema = @Schema(implementation =  UserDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<UserDto> createUser(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_CREATE_USER, required = true) @Validated @RequestBody(required = true) CreateUserDto userDto) throws UserException {
        log.info(String.format(MSG_PROCESS, "init", "create",  userDto.getName()));

        UserDto response = usersFacade.createUser(UserDtoMapper.toEntity(userDto));

        log.info(String.format(MSG_PROCESS, "end", "create",  userDto.getName()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
