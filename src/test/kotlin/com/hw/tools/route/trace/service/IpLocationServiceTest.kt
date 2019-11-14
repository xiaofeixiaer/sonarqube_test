package com.hw.tools.route.trace.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class IpLocationServiceTest {

    @Autowired
    private lateinit var ipLocationService: IpLocationService

    @Test
    fun `should get ip location by ip`() {
        val host = "61.135.169.125"
        val ipLocation = ipLocationService.queryLocation(host)
        assertEquals(ipLocation.ip, host)
    }

}
