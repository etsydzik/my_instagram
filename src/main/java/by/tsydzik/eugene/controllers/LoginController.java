package by.tsydzik.eugene.controllers;

import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by tsyd on 18.03.2015.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
      public String loginForm(User user) {
        return "pages/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@Valid User user, BindingResult result,
                          HttpSession session) {
        if (result.hasErrors()) {
            return "pages/login";
        } else{
            User databaseUser = userService.findUserByEmainAndPassword(user);
            if (databaseUser != null){
                session.setAttribute("me", databaseUser);
                return "redirect:home";
            }else{
                result.reject("user.not_exists", "no user with such login and password");
                return "pages/login";
            }
        } 
    }
}












