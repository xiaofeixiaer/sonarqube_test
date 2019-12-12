package com.hw.tools.route.trace.controller

import com.hw.tools.route.trace.controller.requst.PostPing
import com.hw.tools.route.trace.service.PingService
import com.hw.tools.route.trace.service.data.PingResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class PingController(
        val pingService: PingService
) {

    @PostMapping
    fun ping(@RequestBody ping: PostPing): PingResponse {
        return pingService.ping(ping.host, ping.ttl)
    }
}