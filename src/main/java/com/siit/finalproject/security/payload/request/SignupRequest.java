package com.siit.finalproject.security.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Data
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;

}
