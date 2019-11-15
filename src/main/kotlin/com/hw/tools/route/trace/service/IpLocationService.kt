package com.hw.tools.route.trace.service

import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.nio.charset.StandardCharsets

@Service
class IpLocationService(
        private val restTemplate: RestTemplate = RestTemplate()
) {

    init {
        restTemplate.messageConverters.add(TBMappingJackson2HttpMessageConverter())
    }

    fun queryLocation(host: String): IpLocation {
        val uriVariables = mapOf(HOST_KEY_NAME to host)
        val ipInfoResponse = restTemplate.getForObject<IpInfoResponse>(QUERY_API_URL, uriVariables)
        return ipInfoResponse.data
    }

    companion object {
        const val HOST_KEY_NAME = "ip"
        const val QUERY_API_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip={${HOST_KEY_NAME}}"
    }
}

class TBMappingJackson2HttpMessageConverter() : MappingJackson2HttpMessageConverter() {
    init {
        this.supportedMediaTypes = listOf(MediaType("text", "html", StandardCharsets.UTF_8))
    }
}