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
    fun apiDetail() =
       Docket(DocumentationType.SWAGGER_2).apply {
           select().apis(RequestHandlerSelectors.basePackage("com.api.poi.xyinc")).paths(PathSelectors.any())
                   .build().apiInfo(apiInfo().build())
       }

    fun  apiInfo() : ApiInfoBuilder {
       return ApiInfoBuilder().apply {
           title("Api-Coordenates")
           description("Api que gerencia coordenadas.")
           version("1.0")
           termsOfServiceUrl("Termo de uso: Teste Desmostrativo")
           contact(contact())
       }
    }

    fun contact() = Contact("Guilherme Alves", "N/ha", "guilhermeborgeti@gmail.com")
}