package com.mysample.springwas.sample.thymeleaf;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommonApiUtil {

    private static final String RESULT_SUCCESS = "success";

    private static final String EMPTY_MESSAGE = "";

    public <T> ResponseEntity<ApiResultMessage<T>> createResponseEntity(String requestResult, T responseMessage) {
        return ResponseEntity.ok(new ApiResultMessage<T>(requestResult, responseMessage));
    }

    public <T> ResponseEntity<ApiResultMessage<T>> createSuccessResponseEntity(T responseMessage) {
        return createResponseEntity(RESULT_SUCCESS, responseMessage);
    }

    public ResponseEntity<ApiResultMessage<String>> createSuccessResponseEntity() {
        return createSuccessResponseEntity(EMPTY_MESSAGE);
    }


}
