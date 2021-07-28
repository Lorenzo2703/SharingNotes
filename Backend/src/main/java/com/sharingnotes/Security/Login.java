package com.sharingnotes.Security;

/*

    Per funzionare aggiungere questa dipendenza nel pom.xml

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class Login extends WebSecurityConfigurerAdapter {

    // Uso di springboot security per gestire gli utenti loggati, il primo metodo a configurato per ricercare dentro mongo le credenziali
    // il secondo metodo manca la form di login e il metodo che dà l'ok per l'utente autenticato

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pass")
                .roles("USER")
                .and().
                withUser("a")
                .password("a")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/Security/**","/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .permitAll();
    }
}

*/