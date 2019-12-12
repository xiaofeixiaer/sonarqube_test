package com.hw.tools.route.trace.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.trace")
class TraceProperty {
    lateinit var geoIpDb: String
}