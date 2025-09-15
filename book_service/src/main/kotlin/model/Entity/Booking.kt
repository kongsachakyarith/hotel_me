package org.kshrd.cloud.model.Entity

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table(name = "bookings")
data class Booking(

    val bookingId: Long = 0,
    val userId: Long,
    val room: Room,
    var bookingStatus: String = "PENDING",
    val bookingDate: LocalDateTime = LocalDateTime.now(),
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate
)
