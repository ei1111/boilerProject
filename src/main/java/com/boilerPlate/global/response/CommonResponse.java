package com.boilerPlate.global.response;

import lombok.*;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    private String resultCode;
    private Map playLoad;
}
