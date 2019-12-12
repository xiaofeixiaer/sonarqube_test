package com.hw.tools.route.trace.service.data

data class PingResponse(
        val from: String,
        val seq: Int,
        val ttl: Int,
        val time: String
)