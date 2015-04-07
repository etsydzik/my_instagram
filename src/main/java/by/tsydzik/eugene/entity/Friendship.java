package by.tsydzik.eugene.entity;


import javax.persistence.*;


@Entity
@Table(
		uniqueConstraints=@UniqueConstraint(					//����������� �� ������������
				name="uk_user_id_friend_id", 
				columnNames={"user_id", "friend_id"}
		),
		indexes = {
			@Index(
					name="ix_user_id",
					columnList="user_id"
			),
			@Index(
					name="ix_friend_id",
					columnList="friend_id"
			),
		}
)
public class Friendship {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

	@ManyToOne
	@JoinColumn(name="friend_id")
	private User friend;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}
