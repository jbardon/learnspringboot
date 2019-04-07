package learnspringboot.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* Shorcut for 3 annotation
   - @Configuration
   - @EnableAutoConfiguration
   - @ComponentScan (ex: @ComponentScan(basePackages = {"outside.package"}))

   This file is in learnspringboot.core and not in the api subpackage
   because to enable the component scan for all dependencies
*/
@SpringBootApplication
public class CoreApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApiApplication.class, args);
	}
}