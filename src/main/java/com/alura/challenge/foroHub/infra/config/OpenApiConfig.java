package com.alura.challenge.foroHub.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Contact myContact = new Contact();
        myContact.setName("Jymma Mogollon");
        myContact.setEmail("jkmogollon.cp@gmail.com");

        Info information = new Info()
                .title("Challenge Alura - Foro Hub")
                .description("CRUD API")
                .contact(myContact);

        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .components(
                        new Components()
                                .addSecuritySchemes("bearer-key",
                                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }

}
