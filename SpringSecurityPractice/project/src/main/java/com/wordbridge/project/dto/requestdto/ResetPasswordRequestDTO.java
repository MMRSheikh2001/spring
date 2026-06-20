package com.wordbridge.project.dto.requestdto;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {

    private String token;
    private String newPassword;


}
