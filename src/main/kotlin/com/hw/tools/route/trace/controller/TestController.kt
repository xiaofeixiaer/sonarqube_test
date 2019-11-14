package com.hw.tools.route.trace.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun abc(): String {
        return "test";
    }
}