package com.maraujo.userregister.service;

import com.maraujo.userregister.repository.UserRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterFormExistsByCPFImpl implements RegisterFormExistsByCPF {

    private final UserRegisterRepository userRegisterRepository;

    public boolean execute(String cpf){
        return userRegisterRepository.existsByCpf(cpf);
    }

}
