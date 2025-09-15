//package org.kshrd.cloud.Exception
//
//import org.springframework.boot.autoconfigure.web.WebProperties
//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
//import org.springframework.boot.web.error.ErrorAttributeOptions
//import org.springframework.boot.web.reactive.error.ErrorAttributes
//import org.springframework.context.ApplicationContext
//import org.springframework.core.annotation.Order
//import org.springframework.http.MediaType
//import org.springframework.http.codec.ServerCodecConfigurer
//import org.springframework.stereotype.Component
//import org.springframework.web.reactive.function.BodyInserters
//import org.springframework.web.reactive.function.server.*
//import reactor.core.publisher.Mono
//
//@Component
//@Order(-2) // Higher precedence than DefaultErrorWebExceptionHandler
//class GlobalErrorWebExceptionHandler(
//    errorAttributes: ErrorAttributes,
//    webProperties: WebProperties,
//    applicationContext: ApplicationContext,
//    serverCodecConfigurer: ServerCodecConfigurer
//) : AbstractErrorWebExceptionHandler(errorAttributes, webProperties.resources, applicationContext) {
//
//    init {
//        setMessageWriters(serverCodecConfigurer.writers)
//        setMessageReaders(serverCodecConfigurer.readers)
//    }
//
//    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
//        return RouterFunctions.route(RequestPredicates.all()) { request ->
//            renderErrorResponse(request)
//        }
//    }
//
//    private fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
//        val errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults())
//        val status = errorPropertiesMap["status"] as Int
//
//        return ServerResponse.status(status)
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(BodyInserters.fromValue(errorPropertiesMap))
//    }
//}