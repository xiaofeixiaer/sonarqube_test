package com.hw.tools.route.trace.controller

import com.hw.tools.route.trace.entity.Hello
import com.hw.tools.route.trace.repository.HelloRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloController(
        val helloRepository: HelloRepository
) {

    @GetMapping
    fun get(pageable: Pageable): Page<Hello> {
        return helloRepository.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable(name = "id") hello: Hello): Hello {
        return hello
    }

    @PostMapping
    fun create(@RequestBody hello: Hello): Hello {
        return helloRepository.save(hello)
    }

    @PutMapping("/{id}")
    fun update(
            @RequestBody hello: Hello,
            @PathVariable id: Long
    ): Hello {
        return helloRepository.save(hello.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        helloRepository.deleteById(id)
    }
}