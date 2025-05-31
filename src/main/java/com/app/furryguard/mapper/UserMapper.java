package com.app.furryguard.mapper;


import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.userDtos.UserShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
