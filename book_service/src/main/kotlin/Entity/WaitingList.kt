package org.kshrd.cloud.Entity

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "waiting_list")
data class WaitingList(
    val waitlistId: Long = 0,
    val userId: Long,
    val roomTypeRequested: String,
    val requestDate: LocalDateTime = LocalDateTime.now()

)
