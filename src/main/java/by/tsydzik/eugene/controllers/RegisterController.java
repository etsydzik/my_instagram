package by.tsydzik.eugene.controllers;

import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.repositories.UserRepository;
import by.tsydzik.eugene.service.UserService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by tsyd on 18.03.2015.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerForm(User user) {
        return "pages/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegister(@Valid User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()){
            return "pages/registration";
        }else {
            User registeredUser = userService.registerUser(user);
            if (registeredUser != null){
                session.setAttribute("me", registeredUser);
                return "redirect:home";
            }else {
                result.reject("user.not_exists", "no user with such login and password");
                return "pages/registration";
            }
        }
    }
}
