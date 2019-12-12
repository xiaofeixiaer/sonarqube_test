package com.hw.tools.route.trace.service

import com.hw.tools.route.trace.service.data.IpInfoResponse
import com.hw.tools.route.trace.service.data.IpLocation
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class IpLocationTaoBaoService(
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
