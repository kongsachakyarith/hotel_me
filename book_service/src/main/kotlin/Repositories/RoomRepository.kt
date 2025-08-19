package org.kshrd.cloud.Repositories

import org.kshrd.cloud.Entity.Booking
import org.kshrd.cloud.Entity.Room
import org.kshrd.cloud.Entity.WaitingList
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface RoomRepository : ReactiveCrudRepository<Room, Long> {
    fun findByAvailabilityStatus(status: String): Flux<Room>
}

interface BookingRepository : ReactiveCrudRepository<Booking, Long> {
    fun findByUserId(userId: Long): Flux<Booking>
}


interface WaitingListRepository : ReactiveCrudRepository<WaitingList, Long>