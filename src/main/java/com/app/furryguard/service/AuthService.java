package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.entity.User;
import com.app.furryguard.dto.userDtos.UserLoginDto;
import com.app.furryguard.dto.userDtos.UserSignupDto;
import com.app.furryguard.exceptions.EmailAlreadyExistsException;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String signup(UserSignupDto userSignupDto) {

        if (userRepository.findUserByEmail(userSignupDto.getEmail()).isPresent()){
            log.warn("Email already exists: {}", userSignupDto.getEmail());
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .firstName(userSignupDto.getFirstName())
                .surname(userSignupDto.getSurname())
                .lastName(userSignupDto.getLastName())
                .email(userSignupDto.getEmail())
                .password(userSignupDto.getPassword())
                .dateOfBirth(userSignupDto.getDateOfBirth())
                .build();

        userRepository.save(user);

        return jwtTokenProvider.generateToken(user.getId(), user.getEmail());
    }

    public String login(UserLoginDto userLoginDto) {

        User user = userRepository.findUserByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Пользователь с таким email не найден"));

        if(!user.getPassword().equals(userLoginDto.getPassword())) {
            throw new InvalidCredentialsException("Неверный пароль");
        }

        // Генерация JWT токена
        return jwtTokenProvider.generateToken(user.getId(), user.getEmail());

    }


}
