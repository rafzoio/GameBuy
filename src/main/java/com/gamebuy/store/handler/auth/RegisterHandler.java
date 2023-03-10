package com.gamebuy.store.handler.auth;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;
import com.gamebuy.store.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class RegisterHandler implements HttpHandler {

    /**
     * Handles user registration process. User data is encrypted and added to database.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("RegisterHandler called");

        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

        UserDAO userDAO = new UserDAO();
        LoginService loginService = LoginService.getInstance();

        String username = params.get("username");
        String encryptedPassword = loginService.getMd5Hash(params.get("password"));
        Role role = Role.CUSTOMER;

        User newUser = new User(username, encryptedPassword, role);

        userDAO.addUser(newUser);

        out.write(
                "<html>" +
                        "<meta charset=\"utf-8\">" +
                        "<head> <title>User Registered</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>User Registered</h1>" +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/loginForm\">Login</a></button> " +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/\">Return to Menu</a></button> " +
                        "</div>" +
                        "</body>" +
                        "</html>");
        out.close();


    }
}
