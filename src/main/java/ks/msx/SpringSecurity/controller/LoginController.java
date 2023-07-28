package ks.msx.SpringSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/" , method = RequestMethod.POST)
public class LoginController {

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("success")
    public String successPage(){
        return "success";
    }

    @GetMapping("registerPage")
    public String registerPage(){
        return "register";
    }
}
