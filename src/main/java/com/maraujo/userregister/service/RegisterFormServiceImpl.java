package com.maraujo.userregister.service;


import com.maraujo.userregister.repository.UserRegisterRepository;
import com.maraujo.userregister.service.chain.Executor;
import com.maraujo.userregister.service.chain.ErrorHandler;
import com.maraujo.userregister.service.chain.ValidateAddress;
import com.maraujo.userregister.service.chain.ValidateBirthDate;
import com.maraujo.userregister.service.chain.ValidateCPF;
import com.maraujo.userregister.service.chain.ValidateEmail;
import com.maraujo.userregister.service.chain.ValidateName;
import com.maraujo.userregister.service.chain.ValidatePhone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterFormServiceImpl implements RegisterFormService {

    private final ValidateName validateName;
    private final ValidateBirthDate validateBirthDate;
    private final ValidateCPF validateCPF;
    private final ValidateAddress validateAddress;
    private final ValidatePhone validatePhone;
    private final ValidateEmail validateEmail;
    private final ErrorHandler handler;

    private final UserRegisterRepository userRegisterRepository;

    @Override
    public void execute(RegisterPayload registerPayload){

//        registerPayload = RegisterPayload.builder()
//                .name("Jhon")
//                .birthDate("06/22/2020")
//                .cpf("7310903501")
//                .street("Rua A")
//                .streetNumber("42B")
//                .city("Rio de Janeiro")
//                .state("RJ")
//                .postalCode("12345-001")
//                .phone("(21) 9999-9999")
//                .email("jhondoe@gmail.com")
//                .build();

        Executor<RegisterPayload> executor = new Executor<>(registerPayload);

        executor
                .chain(validateName)
                .chain(validateBirthDate)
                .chain(validateCPF)
                .chain(validateAddress)
                .chain(validatePhone)
                .chain(validateEmail)
                .chain(handler);

        System.out.println(registerPayload);
        userRegisterRepository.save(registerPayload.toEntity());

    }

}

