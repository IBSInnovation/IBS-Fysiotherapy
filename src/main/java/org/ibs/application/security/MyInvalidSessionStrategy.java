/*package org.ibs.application.security;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyInvalidSessionStrategy implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getRequestURI().startsWith("/api")) response.setStatus(470);
        else response.sendRedirect("/api/logout");
    }
}*/
