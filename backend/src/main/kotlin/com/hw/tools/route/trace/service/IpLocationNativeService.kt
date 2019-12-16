package com.hw.tools.route.trace.service

import com.hw.tools.route.trace.config.TraceProperty
import com.hw.tools.route.trace.service.data.IpLocationNative
import com.maxmind.geoip2.DatabaseReader
import com.maxmind.geoip2.exception.AddressNotFoundException
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.net.InetAddress

@Service
class IpLocationNativeService(
        traceProperty: TraceProperty,
        resourceLoader: ResourceLoader
) {

    private final lateinit var reader: DatabaseReader

    init {
        val dbStoreSteam = resourceLoader.getResource(traceProperty.geoIpDb).inputStream

        dbStoreSteam.use {
            this.reader = DatabaseReader.Builder(it)
                    .locales(listOf("zh-CN"))
                    .build()
        }
    }

    fun location(host: String): IpLocationNative {
        return try {
            val response = reader.city(InetAddress.getByName(host))
            IpLocationNative(
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
        } catch (ex: AddressNotFoundException) {
            /*TODO some log.*/
            IpLocationNative.Unknown
        }
    }
}