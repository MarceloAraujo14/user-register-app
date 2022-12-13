package com.maraujo.userregister.controller;

import com.maraujo.userregister.service.RegisterFormService;
import com.maraujo.userregister.service.RegisterPayload;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maraujo.userregister.service.Constants.StateProcess.NEW;
import static com.maraujo.userregister.service.Constants.StateProcess.SUCCESS;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/user-register")
public class UserRegisterApiController implements UserRegisterController{

    private final RegisterFormService registerFormService;

    @PostMapping
    public void register(@RequestBody RegisterPayload payload){
        log.info("M register, payload={}, state={}", payload, NEW);
        registerFormService.execute(payload);
        log.info("M register, payload={}, state={}", payload, SUCCESS);
    }

}
