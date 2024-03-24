package com.boilerPlate.global.error.exception;

import com.boilerPlate.global.error.errorCode.ErrorCode;

public class NotSuccessGetApiException extends BusinessException{
    public NotSuccessGetApiException(ErrorCode errorCode) {
        super(errorCode);
    }
}
