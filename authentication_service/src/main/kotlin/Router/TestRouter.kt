package org.kshrd.cloud.Router

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.kshrd.cloud.Handler.TestHandler
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
open class RouterConfig {

    @Bean
    @RouterOperations(
        RouterOperation(
            path = "",
            method = [RequestMethod.GET],
            beanClass = TestHandler::class,
            beanMethod = "getAll",
            operation = Operation(
                operationId = "getAll",
                tags = ["Room Management"],
                summary = "Get all rooms",
                description = "Retrieves a list of all rooms (summary view)",
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Rooms retrieved successfully",
                    )
                ]
            )
        )
    )
    fun testRouter(testHandle: TestHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .path("/api/v1/rooms") { builder ->
                builder
                    .GET("", testHandle::getAll)
            }
            .build()
    }

}