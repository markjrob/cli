package com.markjr.cli.aop.impl;

import com.markjr.cli.exception.UserNotFoundException;
import com.markjr.cli.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserValidationAspect {

    @Autowired
    private UserService userService;  // หรือ Service ที่มี userList อยู่

    @Before("@annotation(ValidateUserExists) && args(userId,..)")
    public void validateUserId(Long userId) {
        boolean exists = userService.isUserIdExists(userId);
        if (!exists) {
            throw new UserNotFoundException("Not Found: " + userId);
        }
    }
}

