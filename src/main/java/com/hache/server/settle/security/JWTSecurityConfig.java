package com.hache.server.settle.security;

import com.hache.server.settle.security.authentication.AuthManager;
import com.hache.server.settle.security.authentication.UserContextReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class JWTSecurityConfig {

    private static String PREFIX_ENDPOINT_SERVER;

    private static String HOST_CLIENT_METHODS_ALLOWED;

    private static String HOST_CLIENT_ALLOWED;

    private static String URL_PERMIT_ALL;

    private static String HEADERS_PERMIT_ALL;

    @Autowired
    private AuthManager authenticationManager;

    @Autowired
    private UserContextReactive userContextReactive;

    public JWTSecurityConfig(@Value("${app.server.prefix.endpoint}") final String prefix,
                             @Value("${app.security.host.operation.allowed}") final String option,
                             @Value("${app.security.host.client.allowed}") final String client,
                             @Value("${app.url.permit.all}") final String uri_permit,
                             @Value("${app.security.host.headers.allowed}") final String header_permit) {
        PREFIX_ENDPOINT_SERVER = prefix;
        HOST_CLIENT_METHODS_ALLOWED = option;
        HOST_CLIENT_ALLOWED = client;
        URL_PERMIT_ALL = uri_permit;
        HEADERS_PERMIT_ALL = header_permit;
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> Mono.empty();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        final String[] listEndPointPermit = Arrays.stream(URL_PERMIT_ALL.split(",")).map(String::trim).toArray(String[]::new);

        return http
                .authorizeExchange()
                .pathMatchers(listEndPointPermit).permitAll() // Access-Control-Allow-Origin, Access-Control-Allow-Methods, Access-Control-Allow-Headers, Authorization, Content-Type
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(userContextReactive)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final String[] listOpt = Arrays.stream((HOST_CLIENT_METHODS_ALLOWED.split(","))).map(String::trim).toArray(String[]::new);
        final String[] listHostClient = Arrays.stream((HOST_CLIENT_ALLOWED.split(","))).map(String::trim).toArray(String[]::new);
        final String[] listHeadersAccept = Arrays.stream((HEADERS_PERMIT_ALL.split(","))).map(String::trim).toArray(String[]::new);

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(listHostClient));
        configuration.setAllowedMethods(List.of(listOpt));//"GET", "POST", "PUT", "DELETE", "OPTIONS"
        configuration.setAllowedHeaders(List.of(listHeadersAccept));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(PREFIX_ENDPOINT_SERVER, configuration);
        return source;
    }

    /*
        @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);
        return http.build();
    }
    */

    /*
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/static/**")
                .permitAll()
                .and()
                .csrf().disable()
                .cors()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);
        return http.build();
    }
    */

}
