package com.nataliiakoval.onlineshopspringboot.web.filter;

import com.nataliiakoval.onlineshopspringboot.service.SecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends GenericFilterBean {
    private static final List<String> FREE_PATHS = List.of("/", "/signup", "/login", "logout");
    @Autowired
    private SecurityService SECURITY_SERVICE;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        for (String freePath : FREE_PATHS) {
            if(requestURI.endsWith(freePath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        Cookie[] cookies = httpServletRequest.getCookies();

        if (SECURITY_SERVICE.isClientAuth(cookies)) {
            filterChain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}
