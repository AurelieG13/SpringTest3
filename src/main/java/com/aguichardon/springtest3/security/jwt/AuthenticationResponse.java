package com.aguichardon.springtest3.security.jwt;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponse {
    private String token;

}