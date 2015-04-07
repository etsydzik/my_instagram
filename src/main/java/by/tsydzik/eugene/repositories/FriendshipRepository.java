package by.tsydzik.eugene.repositories;

import by.tsydzik.eugene.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tsyd on 24.03.2015.
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long>{

    Friendship findByUserIdAndFriendId(Long userId, Long friendId);

}
