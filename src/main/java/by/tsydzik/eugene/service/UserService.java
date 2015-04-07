package by.tsydzik.eugene.service;

import by.tsydzik.eugene.entity.Friendship;
import by.tsydzik.eugene.entity.User;
import by.tsydzik.eugene.repositories.FriendshipRepository;
import by.tsydzik.eugene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tsyd on 19.03.2015.
 */
@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public User findUserByEmainAndPassword(User user) {
        user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return user;
    }

    public User registerUser(User user) {
        User userFromDatabase = userRepository.findByNameOrEmail(user.getName(), user.getEmail());
        if (userFromDatabase != null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    public List<User> findFriends(Long userId){
        return userRepository.findAllFriends(userId);
    }

    public User setUserAvatar(Long userId, Long photoId){
        User user = userRepository.findOne(userId);
        user.setAvatarId(photoId);
        return userRepository.save(user);
    }

    public void addFriend(Long userId, Long friendId){
        Friendship friendship = new Friendship();
        friendship.setFriend(userRepository.findOne(friendId));
        friendship.setUser(userRepository.findOne(userId));
        friendshipRepository.save(friendship);
    }

    public boolean isFriend(Long userId, Long friendId){                            //для кнопки добавить в друзья
        return friendshipRepository.findByUserIdAndFriendId(userId,friendId) != null;
    }

    public List<User> findUsersByName(String text){
        return userRepository.findByNameStartingWith(text);
    }

}
