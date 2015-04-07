package by.tsydzik.eugene.service;

import by.tsydzik.eugene.entity.Comment;
import by.tsydzik.eugene.entity.Photo;
import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.repositories.CommentRepository;
import by.tsydzik.eugene.repositories.PhotoRepository;
import by.tsydzik.eugene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by tsyd on 18.03.2015.
 */
@Component
@SessionAttributes("user")
public class PhotoService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Photo> findPhotos(Long userId) {
        return photoRepository.findByUserId(userId);
    }

    public Photo createPhoto(byte[] imageBody, Long userId) throws IOException {
        Photo photo = new Photo();
        photo.setDate(new Date());                //запись время создания объекта		в БД
        photo.setUser(userRepository.getOne(userId));       //записали юзера

        photo.setOriginalImage(imageBody);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBody));            //читаем картинку из байт
        photo.setSmallImage(imageService.resize(image, 300, 300));
        photo.setBigImage(imageService.resize(image, 620, 620));
        photo.setSmallAvatarImage(imageService.resize(image, 70, 70));
        return photoRepository.save(photo);
    }

    public void addComment(Long userId,
                           Long photoId,
                           String text) {
        Comment comment = new Comment();
        comment.setPhoto(photoRepository.findOne(photoId));
        comment.setUser(userRepository.findOne(userId));
        comment.setText(text);
        comment.setDate(new Date());
        commentRepository.save(comment);
    }
}
