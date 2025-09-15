package org.kshrd.cloud.model.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal

@Table(name = "rooms")
data class Room (

    @Id
    var roomId: Long = 0,
    var roomName: String = "",
    var roomType: String = "",
    var pricePerNight: BigDecimal = 0.0.toBigDecimal(),
    var availabilityStatus: String = "AVAILABLE"

)