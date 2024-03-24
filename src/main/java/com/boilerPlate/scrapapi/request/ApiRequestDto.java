package com.boilerPlate.scrapapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRequestDto {
    @Schema(description = "이름", example = "홍길동")
    private String name;
}
