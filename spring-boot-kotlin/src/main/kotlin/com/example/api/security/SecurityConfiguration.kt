package com.example.api.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

//@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN")
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .csrf().disable()
    }
}
