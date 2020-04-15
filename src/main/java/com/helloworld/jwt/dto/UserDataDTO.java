package com.helloworld.jwt.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import com.helloworld.jwt.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String password;
    @ApiModelProperty(position = 2)
    List<Role> roles;
}
