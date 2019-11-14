package com.hw.tools.route.trace.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class IpLocationService {

    fun queryLocation(host: String): IpLocation {
        return queryFromTB(host).data
    }

    private fun queryFromTB(host: String): IpInfoResponse {
        val restTemplate = RestTemplate()
        TODO("cant cover result to object. response meta type is text/html;UTF-8")
        return RestTemplate().getForObject(QUERY_API_URL, mapOf("ip" to host))
    }

    companion object {
        const val QUERY_API_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip={ip}"
    }
}
