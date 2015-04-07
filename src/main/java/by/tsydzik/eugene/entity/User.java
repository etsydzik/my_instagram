package by.tsydzik.eugene.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "User.findAllFriends",
                query = "select f.friend from Friendship f where f.user.id = :userId"
        )
})


public class User {

    public static final String PATTERN = "[a-zA-Z0-9]*";
    @Id
    @GeneratedValue            //id ����� �������� ���� ������
    private Long id;

    @Pattern(regexp = PATTERN)
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 40)
    private String email;

    @Pattern(regexp = PATTERN)
    @Size(min = 3, max = 20)
    private String password;

    private Long avatarId;

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
