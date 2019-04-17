package com.example.answer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author ChenGuanzhou
 * @ClassName com.example.answer.Swagger2
 * @Description
 * @date 2017-07-10 22:12:31
 */
@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {


//        //添加head参数start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("token").description("令牌").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        //添加head参数end
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("/api/.*"))
//                .build()
//                .globalOperationParameters(pars)
//                .apiInfo(apiInfo());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.answer.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("浙江工业大学校车管理系统API文档")
                .description("浙江工业大学计算机科学与技术学院、软件学院")
                .termsOfServiceUrl("https://i.csdn.net/#/uc/profile")
                .version("1.0")
                .build();
    }
}

