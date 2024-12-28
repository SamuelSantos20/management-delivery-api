package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerLogin {


    @GetMapping("/login")
    public String pageLogin() {

        return "login";

    }


    //http://localhost:8080/oauth2/authorize?response_type=code&client_id=client1&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauthorized
    @GetMapping("/authorized")
    @ResponseBody
    public String getAuthorizedCode(@RequestParam("code") String code) {

        return "Seu Authorized code: " +code;

    }


}
