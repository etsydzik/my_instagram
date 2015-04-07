package by.tsydzik.eugene.controllers;

import by.tsydzik.eugene.entity.Photo;
import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.repositories.PhotoRepository;
import by.tsydzik.eugene.repositories.UserRepository;
import by.tsydzik.eugene.service.PhotoService;
import by.tsydzik.eugene.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created by tsyd on 18.03.2015.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("me")
public class UserController implements ResourceLoaderAware{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "/{id}/showAvatar", method = RequestMethod.GET)
    public void findAvatarByUserId(@PathVariable("id") Long userId,
                                   HttpServletResponse response,
                                   @RequestParam(defaultValue = "small") String size){
        User user = userRepository.findOne(userId);
        Long avatarId = user.getAvatarId();
        try {
            if (avatarId != null){
                Photo avatar = photoRepository.findOne(avatarId);
                byte[] data;
                switch (size){
                    case "big":
                        data = avatar.getSmallImage();
                        break;
                    default:
                        data = avatar.getSmallAvatarImage();
                }
                response.getOutputStream().write(data);
            }else {
                String resource;
                switch (size){
                    case "big":
                        resource = "classpath:/avatar300x300.jpg";
                        break;
                    default:
                        resource = "classpath:/avatar70x70.jpg";
                }
                InputStream is = resourceLoader.getResource(resource).getInputStream();
                IOUtils.copy(is, response.getOutputStream());               //перегоняет из InputStream в OutputStream
            }
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPeople(@ModelAttribute("me") User me,
                               Model model,
                               @RequestParam(value = "q", required = false) String text){
        if (text == null){
            model.addAttribute("users", userRepository.findByIdNot(me.getId()));
        }else {
            model.addAttribute("users", userRepository.findByNameStartingWith(text));
        }
        model.addAttribute("user", me);
        return "pages/searchPeople";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String userPage(@PathVariable Long userId,
                           Model model,
                           @ModelAttribute("me") User me){
        if (Objects.equals(userId, me.getId())){
            return "redirect:/home";
        }
        model.addAttribute("photos", photoService.findPhotos(userId));
        model.addAttribute("user", userRepository.findOne(userId));
        model.addAttribute("friend", userService.isFriend(me.getId(), userId));
        return "pages/userPage";
    }

    @RequestMapping(value = "/{friendId}/addToFriend", method = RequestMethod.GET)
    public String addToFriend(@PathVariable Long friendId,
                              @ModelAttribute("me") User me) {
        userService.addFriend(me.getId(), friendId);
        return String.format("redirect:/user/%d", friendId);
    }


    @RequestMapping(value = "{userId}/friends", method = RequestMethod.GET)
    public String getFriends(@PathVariable Long userId,
                             @ModelAttribute("me") User me,
                             Model model) {
        model.addAttribute("friends", userService.findFriends(userId));
        model.addAttribute("user", userRepository.findOne(userId));
        model.addAttribute("friend", userService.isFriend(me.getId(), userId));
        return "pages/friends";
    }
}
