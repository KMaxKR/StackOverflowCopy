package ks.msx.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

    /*
    //@GetMapping ->
    // -> redirect us on main page
     */

    @GetMapping
    public void mainPage( HttpServletResponse response) throws IOException {
        response.sendRedirect( "app/posts");
    }
}
