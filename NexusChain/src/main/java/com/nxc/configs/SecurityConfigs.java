package com.nxc.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nxc.filters.CustomAccessDeniedHandler;
import com.nxc.filters.JwtAuthenticationTokenFilter;
import com.nxc.filters.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.nxc.controllers",
        "com.nxc.repository",
        "com.nxc.service"
})
public class SecurityConfigs extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/warehouse/**").hasRole("ADMIN")
                .antMatchers("/api/orders/**").hasAnyRole("SUPPLIER", "DISTRIBUTOR")
                .antMatchers("/api/pay/**").hasAnyRole("SUPPLIER", "DISTRIBUTOR")
                .antMatchers("/api/product/**").hasRole("SUPPLIER")
                .antMatchers("/api/inventory/update/**").hasAnyRole("SUPPLIER", "DISTRIBUTOR")
                .antMatchers("/api/shipments/**").hasRole("CARRIER")
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        http.csrf().disable();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "djga3njzi",
                "api_key", "595946198281489",
                "api_secret", "hd1cRj177f0HVAQ-vSeqG_yT9Y0",
                "secure", true));
    }
}
