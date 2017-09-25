package com.mauwahid.imd.friendsManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket personApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mauwahid.imd.friendsManagement.controller"))
                .build();

    }



    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "IMD Friends Management",
                "REST API for IMD Friends Management",
                "1.0",
                "Terms of service",
                new Contact("mauwahid", "http://imdglobalservices.com", "admin@imdglobalservices.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }

}
