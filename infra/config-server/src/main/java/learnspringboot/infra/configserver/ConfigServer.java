package learnspringboot.infra.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// Turn this application on a Spring Cloud Remote Config server
@EnableConfigServer
@SpringBootApplication
public class ConfigServer {
	public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
	}
}
