package com.aguichardon.springtest3.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "pseudo")
public class AuthenticationRequest {
    private String pseudo;
    private String password;
}

