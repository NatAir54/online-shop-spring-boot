package com.nataliiakoval.onlineshopspringboot.web.controller;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import com.nataliiakoval.onlineshopspringboot.service.ClientService;
import com.nataliiakoval.onlineshopspringboot.service.SecurityService;
import com.nataliiakoval.onlineshopspringboot.web.util.PageGenerator;
import com.nataliiakoval.onlineshopspringboot.web.util.WebUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ClientAuthenticationController {
    private static final PageGenerator PAGE_GENERATOR = PageGenerator.instance();
    private final Logger LOGGER = LoggerFactory.getLogger(OnlineShopController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String getLoginForm() {
        LOGGER.info("Inside getLoginForm of OnlineShopController");
        return PAGE_GENERATOR.getPage("login.html");
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside login of OnlineShopController");
        Client client = WebUtil.getClient(request);

        Map<String, Object> parameters = Map.of("errorMessage", "Data is incorrect. Please try again!");
        String page = PAGE_GENERATOR.getPage("login.html", parameters);

        if (clientService.findByEmail(client.getEmail()) != null) {
            String userToken = securityService.login(client);
            if (userToken != null) {
                Cookie cookie = new Cookie("user-token", userToken);
                response.addCookie(cookie);
                response.sendRedirect("/");
            } else {
                response.getWriter().write(page);
            }
        } else {
            response.getWriter().write(page);
        }
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    @ResponseBody
    public String getSignupForm() {
        LOGGER.info("Inside getSignupForm of OnlineShopController");
        return PAGE_GENERATOR.getPage("signup.html");
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside signup of OnlineShopController");
        Map<String, Object> parameters = Map.of("errorMessage", "Data is incorrect. Please try again!");
        String page = PAGE_GENERATOR.getPage("signup.html", parameters);

        try {
            Client client = WebUtil.getClient(request);
            if (clientService.findByEmail(client.getEmail()) == null) {
                securityService.signup(client);
                response.sendRedirect("/");
            } else {
                response.getWriter().write(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(page);
        }
    }
}
