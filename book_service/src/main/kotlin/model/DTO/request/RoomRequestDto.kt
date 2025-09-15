package org.kshrd.cloud.model.DTO.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class RoomRequestDto(

    @field:NotBlank(message = "Room name is required")
    @field:Size(min = 2, max = 100, message = "Room name must be between 2 and 100 characters")
    @Schema(
        description = "Name of the room",
        example = "Deluxe Ocean View Suite",
        required = true,
        minLength = 2,
        maxLength = 100
    )
    val roomName: String,

    @field:NotBlank(message = "Room type is required")
    @Schema(
        description = "Type of the room",
        example = "DELUXE",
        required = true,
        allowableValues = ["SINGLE", "DOUBLE", "SUITE", "DELUXE"]
    )
    val roomType: String,

    @field:Positive(message = "Price per night must be positive")
    @field:DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @Schema(
        description = "Price per night in USD",
        example = "299.99",
        required = true,
        minimum = "0.01",
        maximum = "10000"
    )
    val pricePerNight: BigDecimal
)