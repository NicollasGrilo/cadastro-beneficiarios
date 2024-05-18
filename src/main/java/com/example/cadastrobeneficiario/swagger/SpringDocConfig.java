package com.example.cadastrobeneficiario.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

    private SecurityScheme createAPIKeyScheme(){
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("Cadastro de Beneficiários")
                        .version("v1")
                        .description("API REST de cadastro de beneficiários de um plano de saúde.")
                )
                .tags(
                        Arrays.asList(
                                new Tag().name("Beneficiarios").description("Beneficiarios message"),
                                new Tag().name("Authentication").description("Autenticação area")
                        )
                );

    }
}
