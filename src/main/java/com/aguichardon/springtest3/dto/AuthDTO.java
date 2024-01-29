package com.aguichardon.springtest3.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDTO {
    private String pseudo;
    private String password;
    private String authority;
}
