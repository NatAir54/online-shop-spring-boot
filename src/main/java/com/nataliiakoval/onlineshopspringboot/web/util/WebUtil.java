package com.nataliiakoval.onlineshopspringboot.web.util;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import com.nataliiakoval.onlineshopspringboot.entity.Goods;
import jakarta.servlet.http.HttpServletRequest;

public class WebUtil {
    public static Client getClient(HttpServletRequest request) {
        Client client = Client.builder().
                email(request.getParameter("email")).
                password(request.getParameter("password")).
                build();
        return client;
    }

    public static Goods getGoods(HttpServletRequest request) {
        Goods good = Goods.builder().
                name(request.getParameter("name")).
                price(Integer.parseInt(request.getParameter("price"))).
                build();
        return good;
    }
}
