package com.van.vanescolarprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport //habilita paginação do spring
public class VanEscolarProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VanEscolarProjetoApplication.class, args);
	}

}
