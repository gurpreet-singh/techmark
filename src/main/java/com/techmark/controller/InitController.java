package com.techmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gurpreets on 29/06/18.
 */
@RestController
public class InitController {

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public @ResponseBody
    String initUsers() {
        return "Hello World";
    }

}
