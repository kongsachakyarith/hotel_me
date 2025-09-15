package org.kshrd.cloud.Router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.kshrd.cloud.Handler.RoomHandler
import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.model.DTO.response.ErrorResponse
import org.kshrd.cloud.model.DTO.response.RoomResponseDto
import org.kshrd.cloud.model.mapper.RoomSummaryDto
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
open class RouterConfig {

    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/api/v1/rooms",
            method = [RequestMethod.POST],
            beanClass = RoomHandler::class,
            beanMethod = "createRoom",
            operation = Operation(
                operationId = "createRoom",
                tags = ["Room Management"],
                summary = "Create a new room",
                description = "Creates a new room with the provided details",
                requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Room details",
                    required = true,
                    content = [Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = RoomRequestDto::class)
                    )]
                ),
                responses = [
                    ApiResponse(
                        responseCode = "201",
                        description = "Room created successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = RoomResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "400",
                        description = "Invalid request data",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "409",
                        description = "Room already exists",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/rooms/{id}",
            method = [RequestMethod.GET],
            beanClass = RoomHandler::class,
            beanMethod = "getRoomById",
            operation = Operation(
                operationId = "getRoomById",
                tags = ["Room Management"],
                summary = "Get room by ID",
                description = "Retrieves a room by its unique identifier",
                parameters = [
                    Parameter(
                        name = "id",
                        description = "Room ID",
                        required = true,
                        `in` = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                        schema = Schema(type = "integer", format = "int64", example = "1")
                    )
                ],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Room found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = RoomResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "404",
                        description = "Room not found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/rooms",
            method = [RequestMethod.GET],
            beanClass = RoomHandler::class,
            beanMethod = "getAll",
            operation = Operation(
                operationId = "getAllRooms",
                tags = ["Room Management"],
                summary = "Get all rooms",
                description = "Retrieves a list of all rooms (summary view)",
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Rooms retrieved successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(type = "array", implementation = RoomSummaryDto::class)
                        )]
                    )
                ]
            )
        )
    )
    fun roomRoutes(roomHandler: RoomHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .path("/api/v1/rooms") { builder ->
                builder
                    .GET("/{id}", roomHandler::getRoomById)
                    .GET("", roomHandler::getAll)
                    .POST("", roomHandler::createRoom)
            }
            .build()
    }
}
