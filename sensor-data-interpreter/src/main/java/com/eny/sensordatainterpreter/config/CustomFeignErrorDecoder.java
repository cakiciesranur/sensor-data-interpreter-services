package com.eny.sensordatainterpreter.config;

import com.eny.sensordatainterpreter.exception.customexceptions.FeignApiClientException;
import com.eny.sensordatainterpreter.exception.customexceptions.FeignApiServerException;
import com.eny.sensordatainterpreter.exception.customexceptions.FeignClientProcessException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        LOGGER.error("Feign client error with requestUrl={} and responseBody={}", requestUrl, responseBody);

        if (responseStatus.is5xxServerError()) {
            return new FeignApiServerException();
        } else if (responseStatus.is4xxClientError()) {
            return new FeignApiClientException();
        } else {
            return new FeignClientProcessException();
        }
    }
}