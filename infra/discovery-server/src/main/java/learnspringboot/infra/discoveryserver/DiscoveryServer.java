package learnspringboot.infra.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Turn this application on a Spring Cloud Netflix Eureka server
// Dashboard on application deployment url
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServer {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServer.class, args);
	}
}
