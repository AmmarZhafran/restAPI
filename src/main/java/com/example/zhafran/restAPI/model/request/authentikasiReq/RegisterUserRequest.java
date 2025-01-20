package com.example.zhafran.restAPI.model.request.authentikasiReq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank
    @Size(min = 2, max = 100)
    private String username;

    @NotBlank
    @Size(min = 2, max = 100)
    private String password;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
}
