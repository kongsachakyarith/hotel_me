package org.kshrd.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookingService {
}

fun main(args: Array<String>){
    runApplication<BookingService>(*args)
}