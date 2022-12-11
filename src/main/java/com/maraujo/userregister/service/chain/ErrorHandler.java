package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.exception.InvalidInputException;
import com.maraujo.userregister.service.RegisterPayload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ErrorHandler implements ExecutorChain<RegisterPayload> {

    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        if (Objects.nonNull(payload.getErrors())) {
            throw  new InvalidInputException(payload.getErrors());
        }
        return payload;
    }
}
