package com.hw.tools.route.trace.controller

import com.hw.tools.route.trace.service.IpLocationService
import com.hw.tools.route.trace.service.PingService
import com.hw.tools.route.trace.service.data.IpLocation
import com.hw.tools.route.trace.service.data.PingResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TracerController(
        val pingService: PingService,
        val ipLocationService: IpLocationService
) {

    @GetMapping("/Tracer")
    fun abc(host: String, ttl: Int): Pair<PingResponse, IpLocation> {
        val pingResponse = pingService.ping(host, ttl)
        val ipLocation = ipLocationService.queryLocation(pingResponse.from)
        return Pair(pingResponse, ipLocation)
    }
}