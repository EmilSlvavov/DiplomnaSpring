package com.tutorial.spring.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMeDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    private String imageName;

}
