package net.bible.code.scroll.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableResourceServer
@Configuration
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/index.jsp*").permitAll()
                .antMatchers("/api/**").access("#oauth2.hasScope('write') and #oauth2.isOAuth() and hasRole('API_USER')")
                .and()
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("oauth/authorize"));
    }
}
