package learnspringboot.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// https://medium.com/@27.rahul.k/build-a-sample-project-with-spring-cloud-using-cloud-config-eureka-zuul-feign-hystrix-and-378b16bcb7c3
// https://piotrminkowski.wordpress.com/2017/02/05/part-1-creating-microservice-using-spring-cloud-eureka-and-zuul/
// https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/
// https://www.baeldung.com/spring-security-oauth-jwt
// https://www.baeldung.com/rest-api-spring-oauth2-angular
// https://github.com/Baeldung/spring-security-oauth/blob/master/README.md
@Configuration
@EnableWebSecurity
public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter
{
    //ResourceServerConfigurerAdapter; for OAuth2 with @EnableResourceServer (trigerred before WebSecurityonfigurerAdapater Order >)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
    }

    // curl http://localhost:8001/customer/1
    // curl -u user:password http://localhost:8001/customer/1
    // curl -H"Authorization: Basic dXNlcjpwYXNzd29yZA==" http://localhost:8001/customer/1
    @Autowired
    public void configureUsers(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")

            // Requires {noop} to set password encoding to no operation (plain text)
            // Because default encoder is not NoOpPasswordEncoder
            .password("{noop}password")
            .roles("USER");
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;

    }
    */
}
