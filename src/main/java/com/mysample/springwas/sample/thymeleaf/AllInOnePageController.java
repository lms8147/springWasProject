package com.mysample.springwas.sample.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf/allinone")
public class AllInOnePageController {

    private static final Logger logger = LoggerFactory.getLogger(AllInOnePageController.class);
    private static final String PATH = "sample/thymeleaf/all_in_one";

    @GetMapping("/index.do")
    public String getIndex() {
        return PATH + "/index";
    }
}