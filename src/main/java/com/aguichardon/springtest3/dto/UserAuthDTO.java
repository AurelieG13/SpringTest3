package com.aguichardon.springtest3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthDTO {
    private UserDTO userDTO;
    private AuthDTO authDTO;
}
