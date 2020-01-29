package com.api.poi.xyinc.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class Swagger {

    @Bean
    fun apiDetail() : Docket {
       var  docket : Docket = Docket(DocumentationType.SWAGGER_2)
        docket.select().apis(RequestHandlerSelectors.basePackage("com.api.poi.xyinc")).paths(PathSelectors.any())
                .build().apiInfo(apiInfo().build())
        return docket
    }

    fun  apiInfo() : ApiInfoBuilder {

         var piInfoBuilder : ApiInfoBuilder = ApiInfoBuilder()

        piInfoBuilder.title("Api-Coordenates");
        piInfoBuilder.description("Api que gerencia coordenadas.");
        piInfoBuilder.version("1.0");
        piInfoBuilder.termsOfServiceUrl("Termo de uso: Teste Desmostrativo");
        piInfoBuilder.contact(contact());

        return piInfoBuilder;
    }

    fun contact() : Contact {
        var cont : Contact = Contact("Guilherme Alves", "N/ha", "guilhermeborgeti@gmail.com")
        return cont
    }
}