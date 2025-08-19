package org.kshrd.cloud.Entity
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "rooms")
data class Room (
    var roomId: Long = 0,
    var roomName: String = "",
    var roomType: String = "",
    var pricePerNight: Double = 0.0,
    var availabilityStatus: String = "AVAILABLE"

)