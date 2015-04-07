package by.tsydzik.eugene.repositories;

import by.tsydzik.eugene.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tsyd on 19.03.2015.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPhotoId(@Param("photoId") Long photoId);
}
