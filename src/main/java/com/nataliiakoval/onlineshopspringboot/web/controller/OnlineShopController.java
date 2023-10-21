package com.nataliiakoval.onlineshopspringboot.web.controller;

import com.nataliiakoval.onlineshopspringboot.entity.Goods;
import com.nataliiakoval.onlineshopspringboot.service.GoodsService;
import com.nataliiakoval.onlineshopspringboot.web.util.PageGenerator;
import com.nataliiakoval.onlineshopspringboot.web.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class OnlineShopController {
    private static final PageGenerator PAGE_GENERATOR = PageGenerator.instance();
    private final Logger LOGGER = LoggerFactory.getLogger(OnlineShopController.class);
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getMainPage() {
        LOGGER.info("Inside getMainPage of OnlineShopController");
        return PAGE_GENERATOR.getPage("shop.html");
    }

    @RequestMapping(path = "/goods/", method = RequestMethod.GET)
    @ResponseBody
    public String getGoodsList() {
        LOGGER.info("Inside getGoodsList of OnlineShopController");
        List<Goods> goods = goodsService.findAll();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);

        return PAGE_GENERATOR.getPage("goods_list.html", hashMap);
    }

    @RequestMapping(path = "/goods/add", method = RequestMethod.GET)
    @ResponseBody
    public String getAddGoodForm() {
        LOGGER.info("Inside getAddGoodForm of OnlineShopController");
        return PAGE_GENERATOR.getPage("add.html");
    }

    @RequestMapping(path = "/goods/add", method = RequestMethod.POST)
    public void addGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside addGood of OnlineShopController");
        try {
            Goods good = WebUtil.getGoods(request);
            goodsService.addGood(good);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = PAGE_GENERATOR.getPage("add.html", parameters);
            response.getWriter().write(page);
        }
    }

    @RequestMapping(path = "/goods/update", method = RequestMethod.GET)
    @ResponseBody
    public String getUpdateGoodForm() {
        LOGGER.info("Inside getUpdateGoodForm of OnlineShopController");
        List<Goods> goods = goodsService.findAll();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);

        return PAGE_GENERATOR.getPage("update.html", hashMap);
    }

    @RequestMapping(path = "/goods/update", method = RequestMethod.POST)
    public void updateGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside updateGood of OnlineShopController");
        try {
            Goods good = WebUtil.getGoods(request);
            int idRequested = Integer.parseInt(request.getParameter("id"));
            goodsService.update(good, idRequested);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = PAGE_GENERATOR.getPage("update.html", parameters);
            response.getWriter().write(page);
        }
    }

    @RequestMapping(path = "/goods/remove", method = RequestMethod.GET)
    @ResponseBody
    public String getRemoveGoodForm() {
        LOGGER.info("Inside getRemoveGoodForm of OnlineShopController");
        List<Goods> goods = goodsService.findAll();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);

        return PAGE_GENERATOR.getPage("remove.html", hashMap);
    }

    @RequestMapping(path = "/goods/remove", method = RequestMethod.POST)
    public void removeGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside removeGood of OnlineShopController");
        try {
            int idForRemove = Integer.parseInt(request.getParameter("id"));
            goodsService.remove(idForRemove);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods id is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = PAGE_GENERATOR.getPage("remove.html", parameters);
            response.getWriter().write(page);
        }
    }
}
