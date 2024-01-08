package com.bazaronline.bazaronline.Swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition

public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("Bazar API - SpringBoot").version("1.0")
                            .contact(new Contact())
                            .contact(new Contact().name("José Quintana").url("https://github.com/josemq07"))
                            .description("API encargada de realizar peticiones a una base de datos para 3 entidades y diferentes endpoints con funcionalidades"));
    }

}

