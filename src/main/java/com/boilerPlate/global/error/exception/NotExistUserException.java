package com.boilerPlate.global.error.exception;

import com.boilerPlate.global.error.errorCode.ErrorCode;

public class NotExistUserException extends BusinessException{
    public NotExistUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
