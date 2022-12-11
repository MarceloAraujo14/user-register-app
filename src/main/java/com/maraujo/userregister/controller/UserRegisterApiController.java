package com.maraujo.userregister.controller;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.service.RegisterFormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user-register")
public class UserRegisterApiController implements UserRegisterController{

    private final RegisterFormService registerFormService;

    @PostMapping
    public void register(@RequestBody RegisterPayload payload){
        registerFormService.execute(payload);
    }

}
