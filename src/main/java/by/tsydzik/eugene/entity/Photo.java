package by.tsydzik.eugene.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(
        indexes = @Index(
                name = "ix_user_id",
                columnList = "user_id"
        )
)
@NamedQuery(
        name = "Photo.findByUserId",
        query = "from Photo where user.id = :userId"
)   //достать все фото по id
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] originalImage;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] smallAvatarImage;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] smallImage;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] bigImage;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(byte[] originalImage) {
        this.originalImage = originalImage;
    }

    public byte[] getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(byte[] smallImage) {
        this.smallImage = smallImage;
    }

    public byte[] getBigImage() {
        return bigImage;
    }

    public void setBigImage(byte[] bigImage) {
        this.bigImage = bigImage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getSmallAvatarImage() {
        return smallAvatarImage;
    }

    public void setSmallAvatarImage(byte[] smallAvatarImage) {
        this.smallAvatarImage = smallAvatarImage;
    }
}
