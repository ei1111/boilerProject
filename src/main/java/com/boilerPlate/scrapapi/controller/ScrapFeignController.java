package com.boilerPlate.scrapapi.controller;

import com.boilerPlate.scrapapi.request.ApiRequestDto;
import com.boilerPlate.scrapapi.infrastructure.service.ScrapFeignService;
import com.boilerPlate.global.response.ResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "02.3o3 API 호출", description = "3o3 API 호출/결정세액 조회 API")
@RestController
@RequiredArgsConstructor
public class ScrapFeignController {
    private final ScrapFeignService scrapFeignService;

    @Tag(name = "02. API 호출")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "API 조회 완료"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"),
    })
    @PostMapping("v1/api")
    public ResponseEntity<ResponseDto> api(@RequestBody ApiRequestDto apiRequestDto) {
        scrapFeignService.scrapingToApi(apiRequestDto);

        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("API 조회 완료")
                        .build());
    }
}
