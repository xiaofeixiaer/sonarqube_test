package com.hw.tools.route.trace.controller

import com.hw.tools.route.trace.service.IpLocationNativeService
import com.hw.tools.route.trace.service.data.IpLocationNative
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IpLocationController(
        val ipLocationNativeService: IpLocationNativeService
) {

    @GetMapping("/ip/location")
    fun getIpLocation(host: String): IpLocationNative {
        return ipLocationNativeService.location(host)
    }
}