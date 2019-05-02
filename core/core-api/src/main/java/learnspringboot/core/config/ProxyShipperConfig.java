package learnspringboot.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
    Bind all properties in application.properties
    having the given prefix
*/
@ConfigurationProperties("feign.proxy-shipper")
@Getter
@Setter
public class ProxyShipperConfig {
    private String url;
}
