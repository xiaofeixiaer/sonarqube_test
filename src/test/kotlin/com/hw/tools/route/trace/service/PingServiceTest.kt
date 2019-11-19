package com.hw.tools.route.trace.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PingServiceTest {

    @Autowired
    private lateinit var pingService: PingService

    @Test
    fun `should return right ping response when ping localhost`() {
        val host = "8.8.8.8"
        val result = pingService.ping(host)
        assertEquals("8.8.8.8", result.from)
    }
}