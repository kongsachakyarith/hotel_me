package org.kshrd.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticationService {
}

fun main(args: Array<String>){
    runApplication<AuthenticationService>(*args)
}