package com.Microservices.AuthServer.config;

import com.Microservices.AuthServer.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {


    private final CustomUserDetailsService customUserDetailsService;
    private final AuthConfig authConfig;

  @Bean
  public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
  }

 @Bean
 public DaoAuthenticationProvider daoAuthenticationProvider(){
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(customUserDetailsService);
      provider.setPasswordEncoder(authConfig.passwordEncoder());
      return provider;
 }
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http.csrf().disable()
               .authorizeHttpRequests()
               .requestMatchers("/auth/**").permitAll()
               .and()
               .authenticationProvider(daoAuthenticationProvider())
               //.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
               .build();

    }


}
