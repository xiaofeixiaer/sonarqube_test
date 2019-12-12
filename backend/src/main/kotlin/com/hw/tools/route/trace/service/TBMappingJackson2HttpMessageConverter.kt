package com.hw.tools.route.trace.service

import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.nio.charset.StandardCharsets

class TBMappingJackson2HttpMessageConverter : MappingJackson2HttpMessageConverter() {
    init {
        this.supportedMediaTypes = listOf(MediaType("text", "html", StandardCharsets.UTF_8))
    }
}