package com.hw.tools.route.trace.service

import com.hw.tools.route.trace.service.data.PingResponse
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.regex.Pattern

@Service
class PingService {

    fun ping(host: String, ttl: Int = 64): PingResponse {
        val command = "ping -c 1 -m $ttl $host"
        val process = Runtime.getRuntime().exec(command)
        val waitFor = process.waitFor(30, TimeUnit.SECONDS)
        if (waitFor) {
            val echo = process.inputStream.bufferedReader().readText()
            return toPingResp(echo)
        } else {
            throw TimeoutException("Timeout, wait for 30 seconds")
        }
    }

    private fun toPingResp(echo: String): PingResponse {
        return PingResponse(
                fromHost(echo),
                seq(echo),
                ttl(echo),
                time(echo)
        )
    }

    fun fromHost(echo: String): String {
        val matcher = Pattern.compile("from [\\d.]+").matcher(echo)
        return if (matcher.find()) {
            matcher.group().substring(5)
        } else {
            throw RuntimeException("Not right ping. maybe lost package.")
        }
    }

    fun seq(echo: String): Int {
        val matcher = Pattern.compile("icmp_seq=[\\d]+").matcher(echo)
        return if (matcher.find()) {
            matcher.group().substring(9).toInt()
        } else {
            0
        }
    }

    fun ttl(echo: String): Int {
        val matcher = Pattern.compile("ttl=[\\d]+").matcher(echo)
        return if (matcher.find()) {
            matcher.group().substring(4).toInt()
        } else {
            0
        }
    }

    fun time(echo: String): String {
        val matcher = Pattern.compile("time=[\\d.]+[ ms]+").matcher(echo)
        return if (matcher.find()) {
            matcher.group()
        } else {
            ""
        }
    }

}