package com.demo.shared.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class IndexController {
    @RequestMapping("/")
    public Map<String, String> index() {
        return Collections.singletonMap("version", IndexController.class.getPackage().getImplementationVersion());
    }
}
