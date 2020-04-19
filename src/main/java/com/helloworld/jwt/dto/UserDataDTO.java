package com.helloworld.jwt.dto;

import java.util.Set;
import io.swagger.annotations.ApiModelProperty;
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
    Set<String> userRoles;
}
