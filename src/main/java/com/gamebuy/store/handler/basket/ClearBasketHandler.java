package com.gamebuy.store.handler.basket;

import com.gamebuy.store.service.BasketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ClearBasketHandler implements HttpHandler {

    /**
     * Handles clearing basket functionality using basketService.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("ClearBasketHandler called");

        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        BasketService basketService = BasketService.getInstance();

        basketService.clearBasket(1);

        out.write(
                "<html>" +
                        "<head> " +
                        "<title>Basket Cleared</title> " +
                        "<meta charset=\"utf-8\">" +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Basket Cleared</h1>" +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/basket\">Back to basket</a></button> " +
                        "</div>" +

                        "</body>" +
                        "</html>");
        out.close();
    }
}
