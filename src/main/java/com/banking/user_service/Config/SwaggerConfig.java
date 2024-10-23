package com.banking.user_service.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public OpenAPI swaggerConfigs(){
        return new OpenAPI().info(
                new Info()
                        .title("Banking App APIs")
                        .description("By Arpit Bodana")
        )
                .servers(List.of(new Server()
                .url("https://banking-monolithic.onrender.com")
                .description("Live on Render")))
                .tags(Arrays.asList(
                        new Tag().name("Account APIs"),
                        new Tag().name("User APIs"),
                        new Tag().name("Payment APIs"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
