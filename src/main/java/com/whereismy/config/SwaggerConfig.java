package com.whereismy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

//swagger: REST 컨트롤러에 명시된 어노테이션을 해석해서 api문서를 자동으로 생성해줌
//ver 2: localhost/swagger-ui.html 로 문서 확인하기
//ver 3: localhost/swagger-ui/index.html 로 문서 확인하기

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket makeDocket() {
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);//Swagger 핵심 클래스
		ApiSelectorBuilder builder = docket.select();//문서 작성을 위한 내부 초기화 작업
		
		//문서를 만들 대상 컨트롤러가 있는 패키지 지정(일반 + rest 컨트롤러 포함 모든 컨트롤러)
		builder = builder.apis(RequestHandlerSelectors.basePackage("com.whereismy.controller"));
		
		//api()로 선택된 api 중 특정 path 조건에 맞는 api를 다시 필터링 하여 문서화 작업을 함() -> rest Controller만
		builder = builder.paths(PathSelectors.ant("/**"));

		docket = builder.build();
		docket = docket.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()));
		return docket;
	}
	
	private ApiInfo apiInfo() {
		ApiInfoBuilder b = new ApiInfoBuilder();
		b = b.title("WhereIsMyHome API입니다.");
		b = b.description("<h3>Todo</h3>Swagger를 이용한 WhereIsMyHome API<br>");
		b = b.version("3.0");
		ApiInfo ai = b.build();
		return ai;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

}
