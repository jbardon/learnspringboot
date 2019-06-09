package learnspringboot.core.config.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Configuration
@EnableWebSecurity
//@EnableResourceServer
//@EnableConfigurationProperties(PvoHttpSecurityProperties.class)
public class PvoSecurityAutoConfiguration
	extends ResourceServerConfigurerAdapter
{
	public static final String PUBKEY_FILE = "pubkey.pem";

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	private PvoHttpSecurityConfigurer pvoHttpSecurityConfigurer;

	@Bean
	public PvoHttpSecurityProperties pvoHttpSecurityProperties() {
		return new PvoHttpSecurityProperties();
	}

	@Bean
	@ConditionalOnMissingBean(PvoHttpSecurityConfigurer.class)
	public PvoHttpSecurityConfigurer defaultSecurityConfigurer(final PvoHttpSecurityProperties pvoHttpSecurityProperties)
	{
		return new PvoHttpSecurityConfigurerDefault(pvoHttpSecurityProperties);
	}

	@Bean
	public TokenStore tokenStore()
	{
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer()
		throws Exception
	{
		JwtAccessTokenConverter converter = new PvoJwtAccessTokenConverter();
		Resource resource = new ClassPathResource(PUBKEY_FILE);
		String publicKey;
		try {
			publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
		} catch (IOException e) {
			throw new Exception("Erreur durant la lecture de la clé publique", e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources)
		throws Exception
	{
		try {
			resources.tokenStore(tokenStore());
		} catch (Exception e) {
			throw new Exception("Erreur durant la configuration du serveur de ressources", e);
		}
	}

	@Override
	public void configure(final HttpSecurity http)
		throws Exception
	{
		pvoHttpSecurityConfigurer.configure(http);
	}
}
