package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.UserLoginDto;
import com.app.furryguard.entity.dto.UserSignupDto;
import com.app.furryguard.exceptions.EmailAlreadyExistsException;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User getUser() {
        return userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));
    }

    public List<Pet> getPets() {

        User user = userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        if (user.getPets() == null)
            throw new RuntimeException("К соажелнию вы еще не добавили ни одного питомца");

        return user.getPets();
    }


}
