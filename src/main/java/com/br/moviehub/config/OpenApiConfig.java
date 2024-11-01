package com.br.moviehub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie Hub")
                        .version("v1.0.0")
                        .description("Application to management Movies and Tv Shows")
                        .termsOfService("https://github.com/AllisonMarquesSouza/MovieHub")
                        .contact(new Contact()
                                .name("Support for email")
                                .email("allisonsouza10261@gmail.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Movie Hub GitHub Documentation")
                        .url("https://github.com/AllisonMarquesSouza/MovieHub")
                ).components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                ).security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth")));

    }
}
