package org.kshrd.cloud.model.DTO.request

import java.time.LocalDate

data class BookingRequestDto (

    val userId: Long,
    val roomId: Long,          // send roomId instead of full Room object
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate

)