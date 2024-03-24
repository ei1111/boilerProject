package com.boilerPlate.user.controller;

import com.boilerPlate.user.infrastructure.service.UsersService;
import com.boilerPlate.user.request.UserRequestDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "01.사용자 저장", description = "사용자 저장")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @Tag(name = "01.사용자 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입완료"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"),
            @ApiResponse(responseCode = "U-001", description = "해당 회원은 존재하지 않습니다.")
    })
    @PostMapping("/v1/members")
    public void save(@RequestBody @Valid UserRequestDto userRequestDto) {
        usersService.save(userRequestDto);
    }
}
