package com.mysample.springwas.sample.thymeleaf;

import lombok.Data;
import lombok.NonNull;

@Data
public class ApiResultMessage<T> {
    @NonNull
    private String requestResult;
    @NonNull
    private T responseMessage;
}
