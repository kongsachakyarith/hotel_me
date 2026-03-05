package org.kshrd.cloud.Repositories

import org.kshrd.cloud.model.Entity.Booking
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface BookingRepository : ReactiveCrudRepository<Booking, Long> {

    fun findByUserId(userId: Long): Flux<Booking>

}