package com.tohjiwa.teamsync.server.config.spring;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.tohjiwa.teamsync.server.service.spring.JpaUserDetailService;
import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final RsaKeyProperties rsaKeys;

    private final JpaUserDetailService jpaUserDetailService;

    public SecurityConfiguration(RsaKeyProperties rsaKeys, JpaUserDetailService jpaUserDetailService) {
        this.rsaKeys = rsaKeys;
        this.jpaUserDetailService = jpaUserDetailService;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .userDetailsService(jpaUserDetailService)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/dashboard/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/dashboard/authentication/**").permitAll()
                        .requestMatchers("/dashboard/assets/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailService)
                .formLogin((formLogin) -> {
                    formLogin.usernameParameter("username");
                    formLogin.passwordParameter("password");
                    formLogin.loginPage("/dashboard/authentication/auth-signin");
                })
                .logout((logout) -> {
                    logout.logoutUrl("/dashboard/authentication/auth-logout");
                    logout.logoutSuccessUrl("/dashboard/authentication/auth-logout");
                    logout.invalidateHttpSession(true);
                    logout.clearAuthentication(true);

                    var cookie = new Cookie("pwsId", null);
                    cookie.setPath("/dashboard/");
                    cookie.setMaxAge(0);
                    logout.addLogoutHandler(new CookieClearingLogoutHandler(cookie));
                });
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain adminApiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/dashboard-api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailService)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
