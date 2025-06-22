package com.app.furryguard.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Schema(description = "User Entity")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            name = "id", example = "141",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Unique user identifier."
    )
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "firstName can't be empty")
    @Size(min = 1, max = 100, message = "Username should be between 1 and 100 characters.")
    @Schema(
            name = "firstName",
            example = "Karam",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Firstname. Should be between 1 and 100 characters."
    )
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "lastName can't be empty")
    @Size(min = 1, max = 100, message = "lastName should be between 1 and 100 characters.")
    @Schema(
            name = "lastName", example = "alexander III",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "lastName. Should be between 1 and 100 characters."
    )
    private String lastName;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "surname can't be empty")
    @Size(min = 1, max = 100, message = "surname should be between 1 and 100 characters.")
    @Schema(
            name = "surname", example = "alexander III",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "surname. Should be between 1 and 100 characters."
    )
    private String surname;

    @Email(message = "Please enter a valid email address.")
    @Column(name = "email", unique = true, nullable = false)
    @Schema(
            name = "email",
            example = "2002@hotmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "User's email. Should be unique and in a valid format."
    )
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 7, max = 100, message = "Password should be between 7 and 100 characters.")
    @Schema(
            name = "password",
            example = "pass123123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "User's password. Should be between 7 and 255 characters.")
    private String password;

    @Past
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @Schema(
            name = "birthday",
            example = "2002-11-04",
            description = "User's birth date. Format: yyyy-MM-dd."
    )
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "ownerId")
    @JsonManagedReference
    private List<Pet> pets;

}
