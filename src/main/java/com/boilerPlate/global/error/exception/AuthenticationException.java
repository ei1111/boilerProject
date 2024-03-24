package com.boilerPlate.global.error.exception;


import com.boilerPlate.global.error.errorCode.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
