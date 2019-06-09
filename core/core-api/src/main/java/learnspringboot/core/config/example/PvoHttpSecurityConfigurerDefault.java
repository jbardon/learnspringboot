package learnspringboot.core.config.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

//@Slf4j
public class PvoHttpSecurityConfigurerDefault
	extends PvoHttpSecurityConfigurer
{
	private final PvoHttpSecurityProperties pvoHttpSecurityProperties;

	@Autowired
	public PvoHttpSecurityConfigurerDefault(final PvoHttpSecurityProperties pvoHttpSecurityProperties)
	{
		this.pvoHttpSecurityProperties = pvoHttpSecurityProperties;
	}

	@Override
	public void configure(final HttpSecurity http)
		throws Exception
	{
		try {
			final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER)
				.and()
				.authorizeRequests();

			final List<String> authorizedRequestsOrder = pvoHttpSecurityProperties.getAuthorizedRequestsOrder();
			if ( !CollectionUtils.isEmpty(authorizedRequestsOrder) ) {
				for (String key : authorizedRequestsOrder) {
					configure(key, urlRegistry);
				}
			} else {
				// Sinon, on ferme tout par défaut
				urlRegistry.anyRequest().denyAll();
			}
		} catch (Exception e) {
			throw new Exception("Erreur durant la configuration de la sécurité", e);
		}
	}

	private void configure(final String key, final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry)
		throws Exception
	{
		final AuthorizedRequest request = pvoHttpSecurityProperties.getAuthorizedRequests().get(key);
		if ( request != null ) {
			ExpressionUrlAuthorizationConfigurer.AuthorizedUrl accessRegistry = registerUrls(urlRegistry, request.getMatchers());
			registerAccesses(accessRegistry, request);
		} else {
			throw new Exception(
				"Les clés de authorized-requests-order doivent avoir une propriétés authorized-requests.<key>. Clé orpheline : " + key);
		}
	}

	private void registerAccesses(final ExpressionUrlAuthorizationConfigurer.AuthorizedUrl accessRegistry, final AuthorizedRequest request)
	{
		// Ensuite 3 cas :
		//   Soit on a un access qui est une chaîne SpEL qui définit des access (soit un mot clé denyAll ou permitALL)
		//   Soit on a des règles (ips, scopes).
		if ( !StringUtils.isEmpty(request.getAccess()) ) {
			accessRegistry.access((request.getAccess()));
		} else {
			// On va les concaténer avec un mot clé "or"
			String scopes = "";
			if ( !CollectionUtils.isEmpty(request.getScopes()) ) {
				scopes = request.getScopes().stream().map(scope -> "#oauth2.hasScope('" + scope + "')").collect(Collectors.joining(" or "));
			}
			String ips = "";
			if ( !CollectionUtils.isEmpty(request.getIps()) ) {
				ips = request.getIps().stream().map(ip -> "hasIpAddress('" + ip + "')").collect(Collectors.joining(" or "));
			}
			StringBuilder accesses = new StringBuilder(scopes);
			accessAppend(accesses, ips);

			accessRegistry.access(accesses.toString());
		}
	}

	private void accessAppend(final StringBuilder accesses, final String newAccess)
	{
		if ( accesses.length() > 0 && !newAccess.isEmpty() ) {
			accesses.append(" or ");
		}
		accesses.append(newAccess);
	}

	private ExpressionUrlAuthorizationConfigurer.AuthorizedUrl registerUrls(final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry,
	                                                                        final List<String> matchers)
	{
		final ExpressionUrlAuthorizationConfigurer.AuthorizedUrl accessRegistry;
		// Plusieurs cas
		// 1/ On n'a qu'un seul matcher et il est égal à anyRequest ou on n'a pas de matchers
		// 2/ On a des matchers
		if ( CollectionUtils.isEmpty(matchers) || ((matchers.size() == 1) && ("anyRequest".equals(matchers.get(0)))) ) {
			// Cas 1
			accessRegistry = urlRegistry.anyRequest();
		} else {
			// Cas 2
			accessRegistry = urlRegistry.antMatchers(matchers.toArray(new String[matchers.size()]));
		}
		return accessRegistry;
	}
}}