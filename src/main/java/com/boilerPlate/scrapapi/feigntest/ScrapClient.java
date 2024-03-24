package com.boilerPlate.scrapapi.feigntest;

import com.boilerPlate.scrapapi.request.ApiRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(url = "https://api호출url", name = "scrapClient")
public interface ScrapClient {
    @PostMapping(value = "/api주소", consumes =  MediaType.APPLICATION_JSON_VALUE)
    Object scraping(ApiRequestDto apiRequestDto);
}
