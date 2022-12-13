package com.maraujo.userregister.service;


import com.maraujo.userregister.exception.RegisterAlreadyExistsException;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CPF_ALREADY_REGISTER;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_EMAIL_ALREADY_REGISTER;
import static com.maraujo.userregister.service.Constants.StateProcess.FAILURE;
import static com.maraujo.userregister.service.Constants.StateProcess.NEW;
import static com.maraujo.userregister.service.Constants.StateProcess.PROCESSED;
import static com.maraujo.userregister.service.Constants.StateProcess.PROCESSING;

@Log4j2
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
    private final RegisterFormExistsByCPF existsByCPF;
    private final RegisterFormExistsByEmail existsByEmail;

    @Override
    public void execute(RegisterPayload payload){
        log.info("M execute, payload={}, state={}", payload, PROCESSING);
        if (existsByCPF.execute(payload.getCpf())){
            log.info("M execute, payload={}, error={}, state={}", payload, ERROR_MSG_CPF_ALREADY_REGISTER, FAILURE);
            throw new RegisterAlreadyExistsException("cpf", ERROR_MSG_CPF_ALREADY_REGISTER);}
        if (existsByEmail.execute(payload.getEmail())){
            log.info("M execute, payload={}, error={}, state={}", payload, ERROR_MSG_EMAIL_ALREADY_REGISTER, FAILURE);
            throw new RegisterAlreadyExistsException("email", ERROR_MSG_EMAIL_ALREADY_REGISTER);
        }

        Executor<RegisterPayload> executor = new Executor<>(payload);

        executor
                .chain(validateName)
                .chain(validateBirthDate)
                .chain(validateCPF)
                .chain(validateAddress)
                .chain(validatePhone)
                .chain(validateEmail)
                .chain(handler);


        userRegisterRepository.save(payload.toEntity());
        log.info("M execute, payload={}, state={}", payload, PROCESSED);
    }

}

