package org.kshrd.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
 open class GatewayService {
}

fun main(args: Array<String>){
    runApplication<GatewayService>(*args)
}