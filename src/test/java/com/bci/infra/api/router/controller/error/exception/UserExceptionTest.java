package com.bci.infra.api.router.controller.error.exception;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserExceptionTest {

    @Test
    void testConstructorAndGetters() {
        UserException ex = new UserException("404", "Usuario no encontrado");

        assertThat(ex.getCode()).isEqualTo("404");
        assertThat(ex.getMessage()).isEqualTo("Usuario no encontrado");
    }
}
