package org.kshrd.cloud.model.DTO.response

import java.time.LocalDateTime

class ErrorResponse (

    val timestamp : LocalDateTime,
    val status : String,
    val errorCode : String,
    val error : String,
    val message : String,
    val path : String
)