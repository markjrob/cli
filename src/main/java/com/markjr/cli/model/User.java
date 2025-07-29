package com.markjr.cli.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    private String phone;
    private String website;

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.website = user.getWebsite();
    }
}
