package ks.msx.SpringSecurity.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @RequestMapping(value = "app/user")
    public String method(Principal principal, Model model){
        User user = userService.returnCurentUser(principal.getName());
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userService.createUser(request.getParameter("username"), request.getParameter("password"));
        response.sendRedirect("/");
        return "main";
    }



}
