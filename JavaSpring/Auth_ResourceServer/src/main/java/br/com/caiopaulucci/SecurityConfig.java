package br.com.caiopaulucci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
class SecurityConfig {

	@Value("${custom.issuer-uri}")
	String issuerUri;
	
	@Bean
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated() // OR .access("authenticated AND hasRole('product_read')")
		.and()
			.oauth2ResourceServer(oauth2ResourceServer ->
		    oauth2ResourceServer.jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri)))
			)
		;
		return http.build();
	}
	

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http .csrf(AbstractHttpConfigurer::disable)
	 * .cors(AbstractHttpConfigurer::disable) .authorizeHttpRequests(authz -> authz
	 * .requestMatchers("/actuator/**").permitAll()
	 * .requestMatchers("/").permitAll() .anyRequest().authenticated())
	 * .oauth2ResourceServer(oauth2 -> oauth2.jwt())
	 * //.oauth2Login(Customizer.withDefaults()) ;
	 * 
	 * return http.build(); }
	 * 
	 */
}