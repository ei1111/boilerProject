package com.boilerPlate.scrapapi.infrastructure.service;


import com.boilerPlate.global.error.exception.NotExistUserException;
import com.boilerPlate.scrapapi.infrastructure.domain.ScrapData;
import com.boilerPlate.scrapapi.feigntest.ScrapClient;
import com.boilerPlate.scrapapi.infrastructure.repository.ScrapFeignRepository;
import com.boilerPlate.scrapapi.request.ApiRequestDto;
import com.boilerPlate.global.error.errorCode.ErrorCode;
import com.boilerPlate.global.error.exception.NotSuccessGetApiException;
import com.boilerPlate.global.utils.Aes256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapFeignService {
    private final ScrapClient scrapClient;
    public ScrapData scrapingToApi(ApiRequestDto apiRequestDto) {
        Map<String, Object> scraping = (Map)scrapClient.scraping(apiRequestDto);
        return new ScrapData();
    }
}
