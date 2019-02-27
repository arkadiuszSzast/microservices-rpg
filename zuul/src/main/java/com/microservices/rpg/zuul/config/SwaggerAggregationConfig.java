package com.microservices.rpg.zuul.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableSwagger2
public class SwaggerAggregationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        SwaggerResource swaggerResourceAccount = new SwaggerResource();
        swaggerResourceAccount.setName("account-service");
        swaggerResourceAccount.setLocation("/account-service/v2/api-docs");
        swaggerResourceAccount.setSwaggerVersion("2.0");

        SwaggerResource swaggerResourceCharacter = new SwaggerResource();
        swaggerResourceCharacter.setName("character-service");
        swaggerResourceCharacter.setLocation("/character-service/v2/api-docs");
        swaggerResourceCharacter.setSwaggerVersion("2.0");

        SwaggerResource swaggerResourcePayment = new SwaggerResource();
        swaggerResourceCharacter.setName("payment-service");
        swaggerResourceCharacter.setLocation("/payment-service/v2/api-docs");
        swaggerResourceCharacter.setSwaggerVersion("2.0");

        resources.add(swaggerResourceAccount);
        resources.add(swaggerResourceCharacter);
        resources.add(swaggerResourcePayment);
        return resources;
    }
}
