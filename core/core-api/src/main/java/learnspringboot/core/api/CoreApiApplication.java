package learnspringboot.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Shorcut for 3 annotation
   - @Configuration
   - @EnableAutoConfiguration
   - @ComponentScan (ex: @ComponentScan(basePackages = {"outside.package"}))
*/
@SpringBootApplication
public class CoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApiApplication.class, args);
	}

	// Les stérotypes permettent de définir des beans dans le contexte Spring: Component, Service, Repository, RestController and Controller

	// commandes maven springboot plugin
	// $ mvn spring-boot:help -Ddetail=true
	// $ mvn spring-boot:run -Dspring-boot.run.arguments=--debug

	// Les scopes des beans (@Scope) : singleton (defaut), prototype (each call), request, session, global session, application, websocket

}
