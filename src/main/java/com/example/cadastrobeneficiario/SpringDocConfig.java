package com.example.cadastrobeneficiario;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Cadastro de Beneficiários")
                        .version("v1")
                        .description("API REST de cadastro de beneficiários de um plano de saúde.")
                )
                .tags(
                        Arrays.asList(
                                new Tag().name("Beneficiarios").description("Beneficiarios message")
                        )
                );

    }
}
