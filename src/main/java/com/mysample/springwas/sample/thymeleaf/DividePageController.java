package com.mysample.springwas.sample.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf/divide")
public class DividePageController {

    private static final Logger logger = LoggerFactory.getLogger(DividePageController.class);
    private static final String PATH = "sample/thymeleaf/divide/contents";

    @GetMapping("/index.do")
    public String getIndex() {
        return PATH + "/index";
    }
}