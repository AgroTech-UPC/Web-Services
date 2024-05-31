package com.acme.web.services.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi() {
// General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("ACME AgroConnect Web Services API")
                        .description("ACME AgroConnect web services application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ACME AgroConnect Web Services wiki Documentation")
                        .url("https://github.com/AgroTech-UPC/Web-Services/tree/main#readme"));
        return openApi;
    }
}


