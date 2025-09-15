package org.kshrd.cloud.Repositories

import org.kshrd.cloud.model.Entity.Room
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RoomRepository : ReactiveCrudRepository<Room, Long> {

    @Query("SELECT * FROM room WHERE availability_status = :status")
    fun findByAvailabilityStatus(status: String): Flux<Room>

    @Query("SELECT * FROM room WHERE room_type = :roomType")
    fun findByRoomType(roomType: String): Flux<Room>

    @Query("SELECT * FROM room WHERE price_per_night BETWEEN :minPrice AND :maxPrice")
    fun findByPricePerNightBetween(minPrice: Double, maxPrice: Double): Flux<Room>

    @Query("SELECT * FROM room WHERE availability_status = 'AVAILABLE' AND price_per_night <= :maxPrice")
    fun findAvailableRoomsUnderPrice(maxPrice: Double): Flux<Room>

    @Query("UPDATE room SET availability_status = :status WHERE room_id = :roomId")
    fun updateRoomStatus(roomId: Long, status: String): Mono<Void>
}