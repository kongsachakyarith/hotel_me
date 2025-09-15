package org.kshrd.cloud.Config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiRouterConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("User Management API")
                    .description("RESTful API for managing user  Spring WebFlux and R2DBC")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("User Management")
                            .email("support@hotel.com")
                            .url("https://github.com/your-repo")
                    )
            )
            .addServersItem(
                Server()
                    .url("http://localhost:8081")
                    .description("Development server")
            )
            .addTagsItem(
                Tag()
                    .name("User Management")
                    .description("Operations related to hotel room management")
            )
    }

}