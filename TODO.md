- Postload in services
- Eureka
- Feign client
- Properties classes
- Configuration


curl https://start.spring.io/starter.zip \\
    -d applicationName=ConfigServer
    -d packageName=learnspringboot.configserver
    -d artifactId=configserver
    -d type=maven-project \\
    -d dependencies=cloud-config-server \\
    -o demo.zip
    
    curl https://start.spring.io/starter.zip -d applicationName=ConfigServer -d packageName=learnspringboot.configserver -d artifactId=configserver -d type=maven-project -d dependencies=cloud-config-server -o demo.zip