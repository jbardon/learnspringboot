package learnspringboot.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/* Shorcut for 3 annotation
   - @Configuration
   - @EnableAutoConfiguration
   - @ComponentScan (ex: @ComponentScan(basePackages = {"outside.package"}))

   This file is in learnspringboot.core and not in the api subpackage
   to enable component scan for all classes under learnspring.core package
*/
@SpringBootApplication
@EnableFeignClients // Implement @FeignClient interfaces to perform rest calls

/*
	Register itself to the Discovery server (same as @EnableEurekaClient)
	Now optional when classpath includes: spring-cloud-starter-netflix-eureka-client
*/
// @EnableDiscoveryClient
public class CoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
