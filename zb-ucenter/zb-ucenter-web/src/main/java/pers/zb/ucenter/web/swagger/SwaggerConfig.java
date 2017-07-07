package pers.zb.ucenter.web.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 访问方式：http(s):// 项目访问路径/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf()).select()
                .apis(RequestHandlerSelectors.basePackage("pers.zb"))// controller路径
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder().title("用户管理中心系统API接口平台").termsOfServiceUrl("http://www.2b2b92b.com")
                .description("提供详细的后台所有Restful接口")
                .contact(new Contact(" [zhoubang] —— 支付技术交流群:470414533，开源中国--项目源码：http://git.oschina.net/zhoubang85/zb，联系方式:842324724@qq.com", null, "Email：842324724@qq.com")).build();

    }

}