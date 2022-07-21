package com.van.vanescolarprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport //habilita paginação do spring
@EnableWebMvc
public class VanEscolarProjetoApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VanEscolarProjetoApplication.class, args);
	}

}
