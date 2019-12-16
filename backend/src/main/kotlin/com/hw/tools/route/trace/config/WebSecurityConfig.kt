package com.hw.tools.route.trace.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)

        http.csrf().disable()
                .formLogin()
                .successHandler { _, _, _ -> println("Success") }
                .failureHandler { _, response, _ ->
                    run {
                        println("Failure")
                        response.status = 401
                    }
                }
    }
}