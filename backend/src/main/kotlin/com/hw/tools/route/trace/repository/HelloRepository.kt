package com.hw.tools.route.trace.repository

import com.hw.tools.route.trace.entity.Hello
import org.springframework.data.jpa.repository.JpaRepository

interface HelloRepository : JpaRepository<Hello, Long>