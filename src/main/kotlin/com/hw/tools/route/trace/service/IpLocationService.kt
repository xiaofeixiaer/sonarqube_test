package com.hw.tools.route.trace.service

import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.nio.charset.StandardCharsets

@Service
class IpLocationService {

    fun queryLocation(host: String): IpLocation {
        return queryFromTB(host).data
    }

    private fun queryFromTB(host: String): IpInfoResponse {
        val restTemplate = RestTemplate()
        restTemplate.messageConverters.add(TBMappingJackson2HttpMessageConverter())
        return restTemplate.getForObject(QUERY_API_URL, mapOf("ip" to host))
    }

    companion object {
        const val QUERY_API_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip={ip}"
    }
}

class TBMappingJackson2HttpMessageConverter() : MappingJackson2HttpMessageConverter() {
    init {
        this.supportedMediaTypes = listOf(MediaType("text", "html", StandardCharsets.UTF_8))
    }
}