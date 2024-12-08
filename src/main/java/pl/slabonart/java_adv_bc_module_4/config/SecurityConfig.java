package pl.slabonart.java_adv_bc_module_4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

    @Value("${spring.security.userName}")
    private String userName;

    @Value("${spring.security.password}")
    private String password;

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername(userName)
                .password(passwordEncoder().encode(password))
                .authorities("read", "write")
                .build();
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                )
                .httpBasic(httpBasicCustomizer()); // Use the customizer here
        return http.build();
    }

    private Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicCustomizer() {
        return httpBasicConfigurer -> httpBasicConfigurer.authenticationEntryPoint(customEntryPoint());
    }

    private BasicAuthenticationEntryPoint customEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Custom Realm");
        return entryPoint;
    }
}
