package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    protected static final String USER_NOT_FOUND = "пользователь не найден";

    public User getUser() {
        return userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException(USER_NOT_FOUND));
    }

    public List<Pet> getPets() {

        User user = userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException(USER_NOT_FOUND));

        if (user.getPets() == null)
            throw new RuntimeException("К соажелнию вы еще не добавили ни одного питомца");

        return user.getPets();
    }


}
