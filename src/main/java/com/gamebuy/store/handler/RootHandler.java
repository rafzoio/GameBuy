package com.gamebuy.store.handler;

import com.gamebuy.store.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RootHandler implements HttpHandler {

    /**
     * Handles root url. Links to login and registration pages.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, 0);

        LoginService loginService = LoginService.getInstance();

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        out.write(
                "<html>" +
                        "<head> <title>Home</title> " +
                        "<meta charset=\"utf-8\">" +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>GameBuy</h1>"
        );


        if (loginService.isUserLoggedIn()) {
            out.write("<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/logout\">Log Out</a></button>");
        } else {
            out.write("<p>Login or register below:</p>");
            out.write("<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/registerForm\">Register</a></button>");
            out.write("<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/loginForm\">Login</a></button>");
        }

        out.write(
                "</div>" +
                        "</body>" +
                        "</html>"
        );
        out.close();

    }


}
