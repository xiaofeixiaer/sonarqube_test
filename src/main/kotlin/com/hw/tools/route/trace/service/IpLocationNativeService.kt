package com.hw.tools.route.trace.service

import com.hw.tools.route.trace.service.data.IpLocationNative
import com.maxmind.geoip2.DatabaseReader
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.net.InetAddress

@Service
class IpLocationNativeService {

    private final var reader: DatabaseReader

    init {
        val dbFile = ResourceUtils.getFile("classpath:geoip/GeoLite2-City.mmdb")
        this.reader = DatabaseReader.Builder(dbFile)
                .locales(listOf("zh-CN"))
                .build()
    }

    fun location(host: String): IpLocationNative {
        val response = reader.city(InetAddress.getByName(host))
        return IpLocationNative(
                city = response.city.name,
                country = response.country.name,
                registeredCountry = response.registeredCountry.name,
                continent = response.continent,
                location = response.location,
                maxMind = response.maxMind,
                postal = response.postal,
                subdivisions = response.subdivisions,
                traits = response.traits
        )
    }
}