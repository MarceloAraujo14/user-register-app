package com.maraujo.userregister.service;

import com.maraujo.userregister.repository.UserRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterFormExistsByEmailImpl implements RegisterFormExistsByEmail {

    private final UserRegisterRepository userRegisterRepository;

    @Override
    public boolean execute(String email) {
        return userRegisterRepository.existsByEmail(email);
    }

}
