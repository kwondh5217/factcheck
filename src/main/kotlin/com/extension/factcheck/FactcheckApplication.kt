package com.extension.factcheck

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FactcheckApplication

fun main(args: Array<String>) {
    runApplication<FactcheckApplication>(*args)
}
