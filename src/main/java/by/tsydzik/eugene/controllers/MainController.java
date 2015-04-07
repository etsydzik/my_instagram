package by.tsydzik.eugene.controllers;

import by.tsydzik.eugene.entity.Photo;
import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.service.PhotoService;
import by.tsydzik.eugene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tsyd on 20.03.2015.
 */
@Controller
@SessionAttributes("me")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goMain(@ModelAttribute("me") User me,
                         Model model) {
        List<Photo> photos = photoService.findPhotos(me.getId());
        model.addAttribute("user", me);
        model.addAttribute("photos", photos);
        return "pages/main";
    }

    @RequestMapping(value = "{id}/friends", method = RequestMethod.GET)
    public String getFriends(@ModelAttribute("me") User me,
                            Model model) {
        List<User> friends = userService.findFriends(me.getId());
        model.addAttribute("friends", friends);
        model.addAttribute("user", me);
        return "pages/myListFriends";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpSession session,
                         ModelMap modelMap) {
        modelMap.remove("me");
        session.removeAttribute("me");
        return "redirect:/";
    }


}
