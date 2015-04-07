package by.tsydzik.eugene.repositories;

import by.tsydzik.eugene.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByUserId(@Param("userId") Long userId);

}
