- Postload in services
- Properties classes
- Configuration
- Zuul
- Security
- Spring boot admin


curl https://start.spring.io/starter.zip \\
    -d applicationName=ConfigServer
    -d packageName=learnspringboot.configserver
    -d artifactId=configserver
    -d type=maven-project \\
    -d dependencies=cloud-config-server \\
    -o demo.zip
    
    curl https://start.spring.io/starter.zip -d applicationName=ConfigServer -d packageName=learnspringboot.configserver -d artifactId=configserver -d type=maven-project -d dependencies=cloud-config-server -o demo.zip
    curl https://start.spring.io/starter.zip -d applicationName=DiscoveryServer -d packageName=learnspringboot.discoveryserver -d artifactId=discoveryserver -d type=maven-project -d dependencies=cloud-eureka-server -o demo.zip