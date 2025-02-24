package com.techronymsuserservice.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private  String username;
    private String email;
    private String password;
    private  String role;

}
