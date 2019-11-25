package com.hw.tools.route.trace.controller

import com.hw.tools.route.trace.service.IpLocationNativeService
import com.hw.tools.route.trace.service.IpLocationTaoBaoService
import com.hw.tools.route.trace.service.data.IpLocation
import com.hw.tools.route.trace.service.data.IpLocationNative
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/location")
class LocationController(
        val ipLocationNativeService: IpLocationNativeService,
        val IpLocationTaoBaoService: IpLocationTaoBaoService
) {

    @GetMapping("/native")
    fun nativeLocation(host: String): IpLocationNative {
        return ipLocationNativeService.location(host)
    }

    @GetMapping("/taobao")
    fun getLocation(host: String): IpLocation {
        return IpLocationTaoBaoService.queryLocation(host)
    }
}