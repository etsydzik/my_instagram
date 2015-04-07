package by.tsydzik.eugene.controllers;

import by.tsydzik.eugene.entity.Comment;
import by.tsydzik.eugene.entity.Photo;
import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.repositories.CommentRepository;
import by.tsydzik.eugene.repositories.PhotoRepository;
import by.tsydzik.eugene.service.PhotoService;
import by.tsydzik.eugene.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by tsyd on 18.03.2015.
 */

@Controller
@SessionAttributes("me")
@RequestMapping("/photo")
public class PhotoController {


    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @Transactional
    public String uploadImage(@RequestParam("photofile") MultipartFile image,
                              @ModelAttribute("me") User me) {
        try {
            photoService.createPhoto(image.getBytes(), me.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/home";                //переход на контроллер, который отображает его загруженную фотку
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getImage(
            @PathVariable Long id,
            HttpServletResponse response,
            @RequestParam(defaultValue = "small") String size) {
        try {
            // get your file as InputStream
            Photo photo = photoRepository.findOne(id);
            byte[] data;
            switch (size){
                case "big":
                    data = photo.getBigImage();
                    break;
                default:
                    data = photo.getSmallImage();
            }
            response.getOutputStream().write(data);
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    /**
     * отображает форму загрузить фото
     *
     * @return
     */
    @RequestMapping(value = "{id}/upload", method = RequestMethod.GET)
    public String uploadForm(@ModelAttribute("me") User me,
                             Model model) {
        model.addAttribute("user", me);
        return "/photo/upload";
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public String getComments(@PathVariable Long id,
                              Model model) {
        List<Comment> comments = commentRepository.findByPhotoId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("photoId", id);
        return "photo/comments";
    }

    @RequestMapping(value = "/{photoId}/avatar", method = RequestMethod.GET)
    public String getAvatar(@ModelAttribute("me") User me,
                            @PathVariable Long photoId) {
        userService.setUserAvatar(me.getId(), photoId);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{photoId}/comments", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String text,
                           @PathVariable Long photoId,
                           @ModelAttribute("me") User me,
                             Model model){
        photoService.addComment(me.getId(), photoId, text);
        return String.format("redirect:/photo/%d/comments", photoId);
    }
}
