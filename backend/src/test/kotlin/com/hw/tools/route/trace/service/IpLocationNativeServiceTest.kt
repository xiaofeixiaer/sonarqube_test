package com.hw.tools.route.trace.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class IpLocationNativeServiceTest {

    @Autowired
    lateinit var ipLocationNativeService: IpLocationNativeService

    @Test
    fun `should get ip location by host`() {
        val host = "61.135.169.125"
        val location = ipLocationNativeService.location(host)
        assertEquals("北京", location.city)
    }

    @Test
    fun `should get unknown message when host is localhost`(){
        val host = "localhost"
        val location = ipLocationNativeService.location(host)
        assertEquals("unknown", location.city)
    }

}