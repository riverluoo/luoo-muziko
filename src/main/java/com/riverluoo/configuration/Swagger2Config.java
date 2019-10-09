package com.riverluoo.configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.riverluoo"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(this.tokenHeader());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("落网 APIs")
                .description("")
                .termsOfServiceUrl("https://riverluoo.com")
                .version("1.0")
                .build();
    }

    private List<Parameter> tokenHeader() {
        Parameter token = new ParameterBuilder()
                .name("luoo-token")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        return Collections.singletonList(token);

    }



}
