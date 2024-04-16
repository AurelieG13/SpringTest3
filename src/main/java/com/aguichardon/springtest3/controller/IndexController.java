package com.aguichardon.springtest3.controller;

import org.springframework.stereotype.Controller;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping({"" ,"/", })
    public ModelAndView redirect() throws IOException {
        LOGGER.info("Redirect...");
        return new ModelAndView("redirect:/index.html");
    }
}
