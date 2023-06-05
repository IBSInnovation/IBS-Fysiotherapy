/*
package org.ibs.application.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * This is a simple redirect.
 * this will only be executed if the cookie is valid, but the user attempts to login.
 * in normal use this isn't executed, because the user wouldn't be logged in. this is caught by {@link nl.v2b.lalaland.security.SecurityConfig}
 *//*

@Controller
@RequestMapping("/api/login")
public class LoginRedirect {
    @Value( "https://localhost:8000/loggedin" )
    private String loginUrl;
    @GetMapping
    public void loginRedirect(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(loginUrl);
    }
}*/
