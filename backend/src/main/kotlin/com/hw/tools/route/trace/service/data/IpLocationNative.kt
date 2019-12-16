package com.hw.tools.route.trace.service.data

import com.maxmind.geoip2.record.*

data class IpLocationNative(
        val city: String,
        val country: String,
        val continent: Continent,
        val location: Location,
        val maxMind: MaxMind,
        val postal: Postal,
        val registeredCountry: String,
        val subdivisions: List<Subdivision>,
        val traits: Traits
) {
    companion object {
        val Unknown = IpLocationNative(
                city = "unknown",
                country = "unknown",
                continent = Continent(),
                location = Location(),
                maxMind = MaxMind(),
                postal = Postal(),
                registeredCountry = "unknown",
                subdivisions = listOf(),
                traits = Traits()
        )
    }
}