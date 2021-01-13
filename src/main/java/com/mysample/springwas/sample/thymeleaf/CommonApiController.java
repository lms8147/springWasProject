package com.mysample.springwas.sample.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@RequestMapping("/thymeleaf/api")
public class CommonApiController {

    private static final Logger logger = LoggerFactory.getLogger(CommonApiController.class);

    @Inject
    private LocaleResolver localeResolver;

    @Inject
    private CommonApiUtil commonApiUtil;


    @GetMapping("/getLanguage.do")
    public ResponseEntity<ApiResultMessage<String>> getSupportLanguages(HttpServletRequest request) {
        String language = "en";
        Locale locale = localeResolver.resolveLocale(request);
        if (locale == Locale.KOREAN) {
            language = "ko";
        }
        return commonApiUtil.createSuccessResponseEntity(language);
    }

    @PostMapping("/setLanguage.do")
    public ResponseEntity<ApiResultMessage<String>> setLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "language", required = true) String language) {
        Locale locale = Locale.ENGLISH;
        if ("ko".equals(language)) {
            locale = Locale.KOREAN;
        }
        localeResolver.setLocale(request, response, locale);
        return commonApiUtil.createSuccessResponseEntity();
    }

}