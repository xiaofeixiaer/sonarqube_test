package com.hw.tools.route.trace.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class IpLocationTaoBaoServiceTest {

    @Autowired
    private lateinit var ipLocationTaoBaoService: IpLocationTaoBaoService

    @Test
    fun `should get ip location by ip`() {
        val host = "61.135.169.125"
        val ipLocation = ipLocationTaoBaoService.queryLocation(host)
        assertEquals(ipLocation.ip, host)
        assertEquals(ipLocation.country, "中国")
        assertEquals(ipLocation.region, "北京")
        assertEquals(ipLocation.city, "北京")
        assertEquals(ipLocation.isp, "联通")
    }
}
