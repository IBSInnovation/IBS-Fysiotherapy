package org.ibs.application.security;

import org.ibs.application.service.UserService;
import org.ibs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
@Component
public class SecurityConfig {
    @Autowired
    private UserService service;
    /*private final LogoutHandler logoutHandler;*/

    /*public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }*/

    public SecurityConfig() {

    }

    @GetMapping
    public Object CurrentUser(OAuth2AuthenticationToken auth2AuthenticationToken){
        return auth2AuthenticationToken.getPrincipal().getAttributes();
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors(); //todo mogelijk beveiligingslek.
        http.headers().frameOptions().disable();
        http.authorizeRequests().and().logout().deleteCookies("SESSION");


//                .antMatchers("*/h2-console/**").permitAll()
//                .antMatchers("*/api/authorization/*").permitAll()
//                .mvcMatchers("*/api/logout").permitAll()
//                .regexMatchers(".*/api/.*").authenticated()
//                .anyRequest().permitAll()
//                .and().oauth2Login()
//                .authorizationEndpoint().baseUri("/api/authorization").and()
//                .redirectionEndpoint()
//                .baseUri("/api/oauth2/callback/*")
//                .and()
//                .failureHandler(failureHandler())
//                .successHandler(createUserOnLogin())
//                .userInfoEndpoint()
//                .oidcUserService(this.oidcUserService())
//                .and().and().logout().deleteCookies("SESSION")
//                // handle logout requests at /logout path
//                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
//                // customize logout handler to log out of Auth0
//                .addLogoutHandler(logoutHandler);
        http.sessionManagement().invalidSessionStrategy(new MyInvalidSessionStrategy());
    }

    /*private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();
        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);
            Optional<User> optional=service.getUserOptional(oidcUser);
            List<SimpleGrantedAuthority> mappedAuthorities;
            if(optional.isEmpty()) {
                mappedAuthorities=List.of(new SimpleGrantedAuthority("ROLE_PLAYER"));
            }
            else {
                User user=optional.get();
                mappedAuthorities=user.getAuthorities();
            }
            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

            return oidcUser;
        };
    }*/

    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/h2-console/**");
    }
}
