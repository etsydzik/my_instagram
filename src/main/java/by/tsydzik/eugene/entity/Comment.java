package by.tsydzik.eugene.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(
		indexes = {
			@Index(
					name="ix_user_id",
					columnList="user_id"
			),
			@Index(
					name="ix_photo_id",
					columnList="photo_id"
			),
		}
)

@NamedQuery(
		name = "Comment.findByPhotoId",
		query = "from Comment where photo.id = :photoId"
)   //достать все комменты по id
public class Comment {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="photo_id")
	private Photo photo;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String text;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
