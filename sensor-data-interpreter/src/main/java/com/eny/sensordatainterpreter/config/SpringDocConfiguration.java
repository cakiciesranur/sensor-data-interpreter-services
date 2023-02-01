package com.eny.sensordatainterpreter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
	private static final String API_TITLE = "Sensor Data Interpreter API";
	private static final String API_DESCRIPTION = "Sensor Data Interpreter Application";
	private static final String CONTACT_NAME = "Esra Nur Yevgi";
	private static final String CONTACT_EMAIL = "cakiciesranur@gmail.com";

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(
                        new Info()
                                .title(API_TITLE)
                                .description(API_DESCRIPTION)
                                .contact(
                                        new Contact()
                                                .name(CONTACT_NAME)
                                                .email(CONTACT_EMAIL)));
    }
}