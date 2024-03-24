package com.boilerPlate.global.error.errorCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //인증 && 인가
      TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001","토큰이 만료되었습니다")
    , NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002","해당 토큰은 유효한 토큰이 아닙니다")
    , NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003" , "Authorization Header가 빈값입니다.")
    , NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED,"A-004" , "인증 타입이 Bearer 타입이 아닙니다.")
    , REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005" , "해당 refresh Token은 존재하지 않습니다." )
    , REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"A-006" , "해당 refresh token은 만료됬습니다." )
    , NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다." )
    , FORBIDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008" ,"관리자 Role이 아닙니다," )

    //회원
    , MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST, "U-001","해당 회원은 존재하지 않습니다." )
    , ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "U-002", "이미 가입된 회원입니다.")
    , PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "U-003", "비밀번호가 일치하지 않습니다.")

    //통신애러
    , NOT_SUCESS_API_CONNECTION(HttpStatus.BAD_REQUEST, "C-001", "API 통신에 문제가 있습니다");
    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}