package com.boilerPlate.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @Schema(description = "회원 아이디", example = "abcd")
    @NotBlank(message = "아이디는 필수입니다")
    private String userId;

    @Schema(description = "비밀번호", example = "1234")
    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @Schema(description = "주민번호", example = "123456-789101")
    @NotBlank(message = "주민번호는 필수입니다")
    private String regNo;

    public UserRequestDto(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }
}
