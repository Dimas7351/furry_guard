package com.app.furryguard.mapper;


import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.AddFileDto;
import com.app.furryguard.entity.dto.UserShortDto;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserShortDto mapToUserShortDto(User user) {

        return UserShortDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

}
